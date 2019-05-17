package wolox.albumes.controllers.interfaces;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

public interface AlbumController {

    @GetMapping(value = {"/albums"}, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity getAlbums(@RequestParam(value = "userId", required = false) String userId);

}
