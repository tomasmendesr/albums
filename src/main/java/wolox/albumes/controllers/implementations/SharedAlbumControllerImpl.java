package wolox.albumes.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wolox.albumes.controllers.interfaces.SharedAlbumController;
import wolox.albumes.dtos.SharedAlbumDTO;
import wolox.albumes.exceptions.InvalidSharedAlbumObjectException;
import wolox.albumes.services.SharedAlbumDataService;
import wolox.albumes.utils.PermissionsConstants;

import java.util.List;

@RestController
public class SharedAlbumControllerImpl implements SharedAlbumController {

    @Autowired
    private SharedAlbumDataService sharedAlbumDataService;

    @Override
    public ResponseEntity getAll() {
        return ResponseEntity.ok(sharedAlbumDataService.findAllSharedAlbums());
    }

    @Override
    public ResponseEntity saveSharedAlbumPermissions(SharedAlbumDTO newSharedAlbum) {
        validateSharedAlbumObject(newSharedAlbum);
        sharedAlbumDataService.saveSharedAlbum(newSharedAlbum);
        return ResponseEntity.ok("Permisos de usuario guardados sobre el album con id " + newSharedAlbum.getAlbumId());
    }

    @Override
    public ResponseEntity saveSharedAlbumPermissionsList(List<SharedAlbumDTO> newSharedAlbumList) {
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
            return ResponseEntity.badRequest().body("Los unicos valores validos para los permisos son: read/write");
        }
        return ResponseEntity.ok(sharedAlbumDataService.getUsersFromSharedAlbumByPermissions(id, permission));
    }

    public void validateSharedAlbumObject(SharedAlbumDTO sharedAlbumDTO){
        String errorMsg = null;
        if(sharedAlbumDTO.getAlbumId() == null) errorMsg = "Es necesario indicar el id del album";
        if(sharedAlbumDTO.getUserId() == null) errorMsg = "Es necesario indicar el id del usuario";
        if(sharedAlbumDTO.getRead() == null) errorMsg = "Es necesario indicar el valor del permiso de lectura";
        if(sharedAlbumDTO.getWrite() == null)  errorMsg = "Es necesario indicar el valor del permiso de escritura";
        if(errorMsg != null) throw new InvalidSharedAlbumObjectException(errorMsg);
    }


}
