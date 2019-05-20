package wolox.albums.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wolox.albums.clients.CommentClient;
import wolox.albums.models.Comment;
import wolox.albums.models.User;
import wolox.albums.utils.DozerHelper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentClient commentClient;

    public List<Comment> getComments(){
        return DozerHelper.mapList(commentClient.getComments(), Comment.class);
    }

    public List<Comment> getCommentsApplyingFilters(Long userId, String name) {
        return getComments().stream()
                .filter(comment -> filterByEqualsName(name, comment))
                .filter(comment -> filterByUser(userId, comment)) // TODO - ningun mail coincide
                .collect(Collectors.toList());
    }

    private boolean filterByEqualsName(String name, Comment comment) {
        try {
            boolean nameFilterApplied = name != null && StringUtils.hasText(name);
            if(nameFilterApplied) name = URLDecoder.decode(name, StandardCharsets.UTF_8.name());
            return !nameFilterApplied || name.equals(comment.getName());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean filterByUser(Long userId, Comment comment){
        User user = userId != null ? userService.getUserById(userId) : null;
        return user == null || user.getEmail().equals(comment.getEmail());
    }

}
