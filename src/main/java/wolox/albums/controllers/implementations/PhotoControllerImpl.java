package wolox.albums.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wolox.albums.controllers.interfaces.PhotoController;
import wolox.albums.services.PhotoService;

@RestController
public class PhotoControllerImpl implements PhotoController {

    @Autowired
    private PhotoService photoService;

    @Override
    public ResponseEntity getPhotos() {
        return ResponseEntity.ok(photoService.getPhotos());
    }
}
