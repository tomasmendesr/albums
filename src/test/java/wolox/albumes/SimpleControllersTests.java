package wolox.albumes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import wolox.albumes.clients.*;
import wolox.albumes.exceptions.UserNotFoundException;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SimpleControllersTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetUsersRequest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(UserClient.GET_USERS_REQUEST)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.OK.value());
    }

    @Test
    public void testGetPhotosRequest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(PhotoClient.GET_PHOTOS_REQUEST)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.OK.value());
    }

    @Test
    public void testGetAlbumsRequest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(AlbumClient.GET_ALBUMES_REQUEST)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.OK.value());
    }

    @Test
    public void testGetAlbumsByUserIdRequest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(AlbumClient.GET_ALBUMES_REQUEST + "?userId=1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.OK.value());
    }

    @Test
    public void testGetAlbumsByUserIdWithWrongParamRequest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(AlbumClient.GET_ALBUMES_REQUEST + "?userId=asd")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value());
        Assert.assertTrue(result.getResolvedException() instanceof MethodArgumentTypeMismatchException);

    }

    @Test
    public void testGetAlbumsByUserIdErrorRequest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(AlbumClient.GET_ALBUMES_REQUEST + "?userId=-1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value());
        Assert.assertTrue(result.getResolvedException() instanceof UserNotFoundException);
    }

    @Test
    public void testGetAlbumsByInvalidUserIdRequest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(AlbumClient.GET_ALBUMES_REQUEST + "?userId=999999")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value());
        Assert.assertTrue(result.getResolvedException() instanceof UserNotFoundException);
    }

    @Test
    public void testGetCommentsRequest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(CommentClient.GET_COMMENTS_REQUEST)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.OK.value());
    }

    @Test
    public void testGetCommentsRequestWithUserId() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(CommentClient.GET_COMMENTS_REQUEST + "?userId=1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.OK.value());
    }

    @Test
    public void testGetCommentsRequestWithName() throws Exception {
        String nameEncoded = URLEncoder.encode("some name", StandardCharsets.UTF_8.name());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(CommentClient.GET_COMMENTS_REQUEST + "?name=" + nameEncoded)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.OK.value());
    }

    @Test
    public void testGetCommentsRequestWithUserIdAndName() throws Exception {
        String nameEncoded = URLEncoder.encode("some name", StandardCharsets.UTF_8.name());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(CommentClient.GET_COMMENTS_REQUEST + "?userId=1&name=" + nameEncoded)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.OK.value());
    }

}
