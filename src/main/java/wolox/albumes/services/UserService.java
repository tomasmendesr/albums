package wolox.albumes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wolox.albumes.clients.UserClient;
import wolox.albumes.exceptions.UserNotFoundException;
import wolox.albumes.models.User;
import wolox.albumes.utils.DozerHelper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserClient userClient;

    public List<User> getUsers(){
        return DozerHelper.mapList(userClient.getUsers(), User.class);
    }

    public User getUserById(Long userId) {
        checkIfUserExistsAndThrowUserNotFoundException(userId);
        return DozerHelper.map(userClient.getUserById(userId), User.class);
    }

    public Boolean userExists(Long userId){
        // El servicio externo responde 404 si recibe un id de usuario inexistente.
        // Por lo tanto prefiero verificarlo en mi modelo y poder lanzar una excepcion propia (UserNotFoundException)
        return getUsers().stream().map(User::getId).collect(Collectors.toList()).contains(userId);
    }

    public void checkIfUserExistsAndThrowUserNotFoundException(Long userId){
        if(!userExists(userId)) throw new UserNotFoundException(userId);
    }


}
