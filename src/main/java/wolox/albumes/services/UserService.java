package wolox.albumes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wolox.albumes.clients.UserClient;
import wolox.albumes.exceptions.UserNotFoundException;
import wolox.albumes.models.User;
import wolox.albumes.utils.DozerHelper;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserClient userClient;

    public List<User> getUsers(){
        return DozerHelper.mapList(userClient.getUsers(), User.class);
    }

    public User getUserById(Long userId) {
        if(!userExists(userId)) throw new UserNotFoundException(userId);
        return DozerHelper.map(userClient.getUserById(userId), User.class);
    }

    public Boolean userExists(Long userId){
        return userClient.getUserById(userId).getId() != null;
    }


}
