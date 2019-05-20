package wolox.albums.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wolox.albums.controllers.interfaces.UserController;
import wolox.albums.services.UserService;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }
}
