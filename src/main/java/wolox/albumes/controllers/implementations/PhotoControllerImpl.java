package wolox.albumes.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wolox.albumes.controllers.interfaces.PhotoController;
import wolox.albumes.services.PhotoService;

@RestController
public class PhotoControllerImpl implements PhotoController {

    @Autowired
    private PhotoService photoService;

    @Override
    public ResponseEntity getPhotos() {
        return ResponseEntity.ok(photoService.getPhotos());
    }
}
