package wolox.albums;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import wolox.albums.clients.GenericRestClient;
import wolox.albums.clients.PostClient;
import wolox.albums.dtos.PostDTO;
import wolox.albums.models.Post;
import wolox.albums.services.PostService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTests {

    @Mock
    PostClient postClient;
    @InjectMocks
    PostService postService;
    @Autowired
    RestTemplate restTemplate;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetPosts(){
        List<PostDTO> posts = Arrays.asList(restTemplate.getForObject(PostClient.EXTERNAL_SERVICE_URL + PostClient.GET_POSTS_REQUEST, PostDTO[].class));
        when(postClient.getPosts()).thenReturn(posts);
        List<Post> fromMyService = postService.getPosts();
        PostDTO[] fromExternalService = restTemplate.getForObject(GenericRestClient.EXTERNAL_SERVICE_URL + PostClient.GET_POSTS_REQUEST, PostDTO[].class);
        Assert.assertEquals(fromExternalService.length, fromMyService.size(), 0);
    }
}
