package wolox.albumes.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wolox.albumes.controllers.interfaces.UserController;
import wolox.albumes.dtos.UserDTO;
import wolox.albumes.services.UserService;

import java.util.List;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity getUsers() {
        List<UserDTO> userList = userService.getUsers();
        return ResponseEntity.ok(userList);
    }
}
