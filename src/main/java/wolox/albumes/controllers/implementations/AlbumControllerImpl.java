package wolox.albumes.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wolox.albumes.controllers.interfaces.AlbumController;
import wolox.albumes.models.Album;
import wolox.albumes.services.AlbumService;

import java.util.List;

@RestController
public class AlbumControllerImpl implements AlbumController {

    @Autowired
    private AlbumService albumService;

    @Override
    public ResponseEntity getAlbums(Long userId) {
        return userId != null ? getAlbumsByUserId(userId) : getAllAlbums() ;
    }

    private ResponseEntity getAllAlbums() {
        return ResponseEntity.ok(albumService.getAlbums());
    }

    private ResponseEntity getAlbumsByUserId(Long userId) {
        List<Album> albumes = albumService.getAlbumsByUserId(userId);
        return ResponseEntity.ok(albumes);
    }

}
