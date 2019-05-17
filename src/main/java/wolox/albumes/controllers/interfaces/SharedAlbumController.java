package wolox.albumes.controllers.interfaces;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wolox.albumes.dtos.SharedAlbumDTO;

import java.util.List;

public interface SharedAlbumController {

    @GetMapping(value = "/sharedAlbums", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity getAll();

    @PostMapping(value = "/sharedAlbums/save", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity saveSharedAlbumPermissions(@RequestBody SharedAlbumDTO newSharedAlbum);

    @PostMapping(value = "/sharedAlbums/saveList", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity saveSharedAlbumPermissionsList(@RequestBody List<SharedAlbumDTO> newSharedAlbumList);

    @GetMapping(value = "/sharedAlbums/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity getSharedAlbumById(@PathVariable Long id);

}
