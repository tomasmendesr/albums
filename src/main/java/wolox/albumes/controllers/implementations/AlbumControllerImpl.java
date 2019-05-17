package wolox.albumes.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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
    public ResponseEntity getAlbumes(String userId) {
        return ValidatorUtil.filterApplied(userId) ? getAlbumesByUserId(userId) : getAllAlbumes() ;
    }

    private ResponseEntity getAllAlbumes() {
        return ResponseEntity.ok(albumService.getAlbumes());
    }

    private ResponseEntity getAlbumesByUserId(String userId) {
        ResponseEntity responseError = ValidatorUtil.validateNumber("userId", userId);
        if(responseError.getStatusCode() != HttpStatus.OK) return responseError;
        List<AlbumDTO> albumes = albumService.getAlbumesByUserId(userId);
        return ResponseEntity.ok(albumes);
    }

}
