package wolox.albumes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wolox.albumes.dtos.CommentDTO;
import wolox.albumes.dtos.UserDTO;
import wolox.albumes.utils.APP_CONFIG;
import wolox.albumes.utils.ValidatorUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    public static final String GET_COMMENTS_REQUEST = "/comments";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;

    public List<CommentDTO> getComments(){
        return Arrays.asList(restTemplate.getForObject(getCommentsRequest(), CommentDTO[].class));
    }

    private String getCommentsRequest(){
        return APP_CONFIG.EXTERNAL_SERVICE_URL + GET_COMMENTS_REQUEST;
    }

    public List<CommentDTO> getCommentsApplyingFilters(String userId, String name) throws UnsupportedEncodingException {
        UserDTO user = ValidatorUtil.filterApplied(userId) ? userService.getUserById(userId) : null;
        boolean nameFilterApplied = ValidatorUtil.filterApplied(name);
        if(nameFilterApplied) name = URLDecoder.decode(name, StandardCharsets.UTF_8.name());
        String finalNameDecoded = name;
        List<CommentDTO> comments = getComments();
        List<CommentDTO> commentsResult = comments.stream()
                .filter(comment -> !nameFilterApplied || finalNameDecoded.equals(comment.getName()))
                .filter(comment -> user == null || user.getEmail().equals(comment.getEmail()))
                .collect(Collectors.toList());
        return commentsResult;
    }

}
