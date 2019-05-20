package wolox.albums;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import wolox.albums.clients.GenericRestClient;
import wolox.albums.clients.UserClient;
import wolox.albums.dtos.UserDTO;
import wolox.albums.models.User;
import wolox.albums.services.*;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    UserService userService;

    @Test
    public void testGetUsers(){
        List<User> fromMyService = userService.getUsers();
        UserDTO[] fromExternalService = restTemplate.getForObject(GenericRestClient.EXTERNAL_SERVICE_URL + UserClient.GET_USERS_REQUEST, UserDTO[].class);
        Assert.assertEquals(fromExternalService.length, fromMyService.size(), 0);
    }

    @Test
    public void testGetUserById(){
        User user = userService.getUserById(2L);
        Assert.assertTrue(user != null && user.getId() == 2L);
    }

}
