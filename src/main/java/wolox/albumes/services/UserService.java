package wolox.albumes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wolox.albumes.clients.UserClient;
import wolox.albumes.dtos.UserDTO;
import wolox.albumes.utils.APP_CONFIG;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserClient userClient;

    public List<UserDTO> getUsers(){
        return userClient.getUsers();
    }

    public UserDTO getUserById(String userId) {
        return userClient.getUserById(userId);
    }

}
