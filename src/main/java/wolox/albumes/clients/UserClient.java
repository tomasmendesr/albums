package wolox.albumes.clients;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import wolox.albumes.dtos.UserDTO;

import java.util.Arrays;
import java.util.List;

@Component
public class UserClient extends GenericRestClient{

    public static final String GET_USERS_REQUEST = "/users";

    public UserClient(RestTemplateBuilder restTemplateBuilder) {
        super(restTemplateBuilder);
    }

    public List<UserDTO> getUsers(){
        return Arrays.asList(restTemplate.getForObject(getUsersRequest(), UserDTO[].class));
    }

    private String getUsersRequest(){
        return EXTERNAL_SERVICE_URL + GET_USERS_REQUEST;
    }

    public UserDTO getUserById(Long userId) {
        return restTemplate.getForObject(getUserByIdRequest(userId), UserDTO.class);
    }

    private String getUserByIdRequest(Long userId){
        return getUsersRequest() + "/" + userId;
    }
}
