package wolox.albumes;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import wolox.albumes.clients.CommentClient;
import wolox.albumes.clients.GenericRestClient;
import wolox.albumes.dtos.CommentDTO;
import wolox.albumes.models.Comment;
import wolox.albumes.services.CommentService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CommentService commentService;

    @Test
    public void testGetComments(){
        List<Comment> commentsFromService = commentService.getComments();
        CommentDTO[] commentsFromExternalService = restTemplate.getForObject(GenericRestClient.EXTERNAL_SERVICE_URL + CommentClient.GET_COMMENTS_REQUEST, CommentDTO[].class);
        Assert.assertEquals(commentsFromExternalService.length, commentsFromService.size(), 0);
    }

    @Test
    public void testGetCommentsWithFilterName() throws UnsupportedEncodingException {
        String nameFilter = "id labore ex et quam laborum";
        List<Comment> comments = commentService.getCommentsApplyingFilters(null, URLEncoder.encode(nameFilter, StandardCharsets.UTF_8.name()));
        Assert.assertEquals(1, comments.size(), 0);
    }

    @Test
    public void testGetCommentsWithFilterUserId() {
        List<Comment> comments = commentService.getCommentsApplyingFilters(1L, null);
        // No coincide ningun email de los comentarios con los de los usuarios
        Assert.assertEquals(0, comments.size(), 0);
    }
}
