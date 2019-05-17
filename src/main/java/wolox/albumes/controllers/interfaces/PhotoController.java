package wolox.albumes.controllers.interfaces;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public interface PhotoController {

    @GetMapping(value = {"/photos"}, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity getPhotos();
}
