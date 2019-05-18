package wolox.albumes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wolox.albumes.clients.UserClient;
import wolox.albumes.dtos.UserDTO;
import wolox.albumes.exceptions.UserNotFoundException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserClient userClient;

    public List<UserDTO> getUsers(){
        return userClient.getUsers();
    }

    public UserDTO getUserById(Long userId) {
        if(!userExists(userId)) throw new UserNotFoundException(userId);
        return userClient.getUserById(userId);
    }

    public Boolean userExists(Long userId){
        return userClient.getUserById(userId).getId() != null;
    }

}
