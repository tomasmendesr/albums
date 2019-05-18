package wolox.albumes.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import wolox.albumes.dtos.UserDTO;
import wolox.albumes.utils.APP_CONFIG;

import java.util.Arrays;
import java.util.List;

@Component
public class UserClient {

    public static final String GET_USERS_REQUEST = "/users";

    @Autowired
    private RestTemplate restTemplate;

    public List<UserDTO> getUsers(){
        return Arrays.asList(restTemplate.getForObject(getUsersRequest(), UserDTO[].class));
    }

    private String getUsersRequest(){
        return APP_CONFIG.EXTERNAL_SERVICE_URL + GET_USERS_REQUEST;
    }

    public UserDTO getUserById(Long userId) {
        return restTemplate.getForObject(getUserByIdRequest(userId), UserDTO.class);
    }

    private String getUserByIdRequest(Long userId){
        return getUsersRequest() + "/" + userId;
    }
}
