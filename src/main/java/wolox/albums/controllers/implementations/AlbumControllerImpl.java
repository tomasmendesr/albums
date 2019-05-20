package wolox.albums.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wolox.albums.controllers.interfaces.AlbumController;
import wolox.albums.services.AlbumService;
import wolox.albums.services.UserService;

@RestController
public class AlbumControllerImpl implements AlbumController {

    @Autowired
    private AlbumService albumService;
    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity getAlbums(Long userId) {
        return userId != null ? getAlbumsByUserId(userId) : getAllAlbums() ;
    }

    private ResponseEntity getAllAlbums() {
        return ResponseEntity.ok(albumService.getAlbums());
    }

    private ResponseEntity getAlbumsByUserId(Long userId) {
        userService.checkIfUserExistsAndThrowUserNotFoundException(userId);
        return ResponseEntity.ok(albumService.getAlbumsByUserId(userId));
    }

}
