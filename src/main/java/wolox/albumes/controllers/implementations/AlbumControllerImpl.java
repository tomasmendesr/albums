package wolox.albumes.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wolox.albumes.controllers.interfaces.AlbumController;
import wolox.albumes.dtos.AlbumDTO;
import wolox.albumes.services.AlbumService;
import wolox.albumes.utils.ValidatorUtil;

import java.util.List;

@RestController
public class AlbumControllerImpl implements AlbumController {

    @Autowired
    private AlbumService albumService;

    @Override
    public ResponseEntity getAlbums(String userId) {
        return ValidatorUtil.filterApplied(userId) ? getAlbumsByUserId(userId) : getAllAlbums() ;
    }

    private ResponseEntity getAllAlbums() {
        return ResponseEntity.ok(albumService.getAlbums());
    }

    private ResponseEntity getAlbumsByUserId(String userId) {
        ResponseEntity responseError = ValidatorUtil.validateNumber("userId", userId);
        if(responseError.getStatusCode() != HttpStatus.OK) return responseError;
        List<AlbumDTO> albumes = albumService.getAlbumsByUserId(userId);
        return ResponseEntity.ok(albumes);
    }

}
