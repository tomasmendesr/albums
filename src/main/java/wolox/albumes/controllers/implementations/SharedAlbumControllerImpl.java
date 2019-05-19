package wolox.albumes.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wolox.albumes.controllers.interfaces.SharedAlbumController;
import wolox.albumes.dtos.SharedAlbumDataDTO;
import wolox.albumes.exceptions.InvalidPermissionException;
import wolox.albumes.exceptions.InvalidSharedAlbumObjectException;
import wolox.albumes.services.SharedAlbumDataService;
import wolox.albumes.models.PermissionsConstants;

import java.util.List;

@RestController
public class SharedAlbumControllerImpl implements SharedAlbumController {

    @Autowired
    private SharedAlbumDataService sharedAlbumDataService;

    @Override
    public ResponseEntity getAllSharedAlbums() {
        return ResponseEntity.ok(sharedAlbumDataService.findAllSharedAlbums());
    }

    @Override
    public ResponseEntity getAllSharedAlbumsData() {
        return ResponseEntity.ok(sharedAlbumDataService.findAll());
    }

    @Override
    public ResponseEntity saveSharedAlbumPermissions(SharedAlbumDataDTO newSharedAlbum) {
        validateSharedAlbumObject(newSharedAlbum);
        sharedAlbumDataService.saveSharedAlbum(newSharedAlbum);
        return ResponseEntity.ok("Permisos de usuario guardados sobre el album con id " + newSharedAlbum.getAlbumId());
    }

    @Override
    public ResponseEntity saveSharedAlbumPermissionsList(List<SharedAlbumDataDTO> newSharedAlbumList) {
        if(newSharedAlbumList == null || newSharedAlbumList.size() == 0) return ResponseEntity.badRequest().body("No se recibió ningún registro");
        newSharedAlbumList.forEach(n -> validateSharedAlbumObject(n));
        sharedAlbumDataService.saveSharedAlbumList(newSharedAlbumList);
        return ResponseEntity.ok("Permisos de usuario guardados");
    }

    @Override
    public ResponseEntity getSharedAlbumById(Long id) {
        return ResponseEntity.ok(sharedAlbumDataService.getSharedAlbumById(id));
    }

    @Override
    public ResponseEntity getUsersFromSharedAlbumByPermissions(Long id, String permission) {
        if (permission == null || (!PermissionsConstants.READ.equals(permission) && !PermissionsConstants.WRITE.equals(permission))) {
            throw new InvalidPermissionException(permission);
        }
        return ResponseEntity.ok(sharedAlbumDataService.getUsersFromSharedAlbumByPermissions(id, permission));
    }

    public void validateSharedAlbumObject(SharedAlbumDataDTO sharedAlbumDataDTO){
        String errorMsg = null;
        if(sharedAlbumDataDTO.getAlbumId() == null) errorMsg = "Es necesario indicar el id del album";
        if(sharedAlbumDataDTO.getUserId() == null) errorMsg = "Es necesario indicar el id del usuario";
        if(sharedAlbumDataDTO.getRead() == null) errorMsg = "Es necesario indicar el valor del permiso de lectura";
        if(sharedAlbumDataDTO.getWrite() == null)  errorMsg = "Es necesario indicar el valor del permiso de escritura";
        if(errorMsg != null) throw new InvalidSharedAlbumObjectException(errorMsg);
    }


}
