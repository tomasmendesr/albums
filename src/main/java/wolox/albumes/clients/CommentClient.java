package wolox.albumes.clients;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import wolox.albumes.dtos.CommentDTO;

import java.util.Arrays;
import java.util.List;

@Component
public class CommentClient extends GenericRestClient {

    public static final String GET_COMMENTS_REQUEST = "/comments";

    public CommentClient(RestTemplateBuilder restTemplateBuilder) {
        super(restTemplateBuilder);
    }

    public List<CommentDTO> getComments(){
        return Arrays.asList(restTemplate.getForObject(getCommentsRequest(), CommentDTO[].class));
    }

    private String getCommentsRequest(){
        return EXTERNAL_SERVICE_URL + GET_COMMENTS_REQUEST;
    }
}
