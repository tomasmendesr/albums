package wolox.albumes.controllers.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wolox.albumes.dtos.SharedAlbumDTO;


@RestController
public interface SharedAlbumController {

    @GetMapping("/sharedAlbums")
    ResponseEntity getAll();

    @PostMapping("/sharedAlbums")
    ResponseEntity newSharedAlbum(@RequestBody SharedAlbumDTO newSharedAlbum);

    @GetMapping("/sharedAlbums/{id}")
    ResponseEntity getSharedAlbumById(@PathVariable Long id);

}
