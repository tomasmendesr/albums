package wolox.albumes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wolox.albumes.clients.UserClient;
import wolox.albumes.dtos.UserDTO;

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

    public Boolean userExists(Long userId){
        return false;
//        return userClient.getUserById(userId);
    }

}
