package wolox.albumes.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import wolox.albumes.dtos.CommentDTO;
import wolox.albumes.utils.APP_CONFIG;

import java.util.Arrays;
import java.util.List;

@Component
public class CommentClient {

    public static final String GET_COMMENTS_REQUEST = "/comments";

    @Autowired
    private RestTemplate restTemplate;

    public List<CommentDTO> getComments(){
        return Arrays.asList(restTemplate.getForObject(getCommentsRequest(), CommentDTO[].class));
    }

    private String getCommentsRequest(){
        return APP_CONFIG.EXTERNAL_SERVICE_URL + GET_COMMENTS_REQUEST;
    }
}
