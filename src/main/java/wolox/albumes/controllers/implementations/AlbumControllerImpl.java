package wolox.albumes.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wolox.albumes.controllers.interfaces.AlbumController;
import wolox.albumes.services.AlbumService;
import wolox.albumes.utils.ValidatorUtil;

@RestController
public class AlbumControllerImpl implements AlbumController {

    @Autowired
    private AlbumService albumService;

    @Override
    public ResponseEntity getAlbums(Long userId) {
        return ValidatorUtil.filterApplied(userId) ? getAlbumsByUserId(userId) : getAllAlbums() ;
    }

    private ResponseEntity getAllAlbums() {
        return ResponseEntity.ok(albumService.getAlbums());
    }

    private ResponseEntity getAlbumsByUserId(Long userId) {
//        ResponseEntity responseError = ValidatorUtil.validateNumber("userId", userId);
//        if(responseError.getStatusCode() != HttpStatus.OK) return responseError;
//        List<AlbumDTO> albumes = albumService.getAlbumsByUserId(userId);
        return ResponseEntity.ok("");//albumes);
    }

}
