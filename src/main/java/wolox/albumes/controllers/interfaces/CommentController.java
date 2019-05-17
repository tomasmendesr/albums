package wolox.albumes.controllers.interfaces;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface CommentController {

    @GetMapping(value = {"/comments"}, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity getComments(@RequestParam(value = "userId", required = false) String userId,
                              @RequestParam(value = "name", required = false) String name);
}
