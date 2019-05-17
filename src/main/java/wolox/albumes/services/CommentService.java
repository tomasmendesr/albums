package wolox.albumes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wolox.albumes.clients.CommentClient;
import wolox.albumes.dtos.CommentDTO;
import wolox.albumes.dtos.UserDTO;
import wolox.albumes.utils.ValidatorUtil;

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

    public List<CommentDTO> getComments(){
        return commentClient.getComments();
    }

    public List<CommentDTO> getCommentsApplyingFilters(String userId, String name) throws UnsupportedEncodingException {
        UserDTO user = ValidatorUtil.filterApplied(userId) ? userService.getUserById(userId) : null;
        boolean nameFilterApplied = ValidatorUtil.filterApplied(name);
        if(nameFilterApplied) name = URLDecoder.decode(name, StandardCharsets.UTF_8.name());
        String finalNameDecoded = name;
        List<CommentDTO> comments = getComments();
        List<CommentDTO> commentsResult = comments.stream()
                .filter(comment -> !nameFilterApplied || finalNameDecoded.equals(comment.getName()))
                .filter(comment -> user == null || user.getEmail().equals(comment.getEmail())) // TODO - ningun mail coincide
                .collect(Collectors.toList());
        return commentsResult;
    }

}
