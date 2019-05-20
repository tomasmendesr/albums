package wolox.albums.controllers.interfaces;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface UserController {

    @GetMapping(value = {"/users"}, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity getUsers();

}
