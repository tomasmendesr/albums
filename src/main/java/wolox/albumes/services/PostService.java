package wolox.albumes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wolox.albumes.dtos.PostDTO;
import wolox.albumes.utils.APP_CONFIG;

import java.util.Arrays;
import java.util.List;

@Service
public class PostService {
    public static final String GET_POSTS_REQUEST = "/posts";

    @Autowired
    private RestTemplate restTemplate;

    public List<PostDTO> getPosts(){
        return Arrays.asList(restTemplate.getForObject(getPostsRequest(), PostDTO[].class));
    }

    private String getPostsRequest(){
        return APP_CONFIG.EXTERNAL_SERVICE_URL + GET_POSTS_REQUEST;
    }
}
