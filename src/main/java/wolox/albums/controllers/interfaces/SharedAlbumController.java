package wolox.albums.controllers.interfaces;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import wolox.albums.dtos.SharedAlbumDataDTO;

import java.util.List;

public interface SharedAlbumController {

    @GetMapping(value = "/sharedAlbums", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity getAllSharedAlbums();

    @GetMapping(value = "/sharedAlbumsData", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity getAllSharedAlbumsData();

    @PostMapping(value = "/sharedAlbums/save", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity saveSharedAlbumPermissions(@RequestBody SharedAlbumDataDTO newSharedAlbum);

    @PostMapping(value = "/sharedAlbums/saveList", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity saveSharedAlbumPermissionsList(@RequestBody List<SharedAlbumDataDTO> newSharedAlbumList);

    @GetMapping(value = "/sharedAlbums/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity getSharedAlbumById(@PathVariable Long id);

    @GetMapping(value = "/sharedAlbums/{id}/{permission}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity getUsersFromSharedAlbumByPermissions(@PathVariable Long id, @PathVariable String permission);


}
