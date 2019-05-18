package wolox.albumes.clients;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import wolox.albumes.dtos.PostDTO;

import java.util.Arrays;
import java.util.List;

@Component
public class PostClient extends GenericRestClient {
    public static final String GET_POSTS_REQUEST = "/posts";

    public PostClient(RestTemplateBuilder restTemplateBuilder) {
        super(restTemplateBuilder);
    }

    public List<PostDTO> getPosts(){
        return Arrays.asList(restTemplate.getForObject(getPostsRequest(), PostDTO[].class));
    }

    private String getPostsRequest(){
        return EXTERNAL_SERVICE_URL + GET_POSTS_REQUEST;
    }
}
