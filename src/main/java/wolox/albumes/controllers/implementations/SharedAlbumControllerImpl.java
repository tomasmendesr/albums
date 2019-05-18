package wolox.albumes.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wolox.albumes.controllers.interfaces.SharedAlbumController;
import wolox.albumes.dtos.SharedAlbumDTO;
import wolox.albumes.exceptions.AlbumNotFoundException;
import wolox.albumes.exceptions.SharedAlbumNotFoundException;
import wolox.albumes.services.SharedAlbumService;
import wolox.albumes.utils.PermissionsConstants;

import java.util.List;

@RestController
public class SharedAlbumControllerImpl implements SharedAlbumController {

    @Autowired
    private SharedAlbumService sharedAlbumService;

    @Override
    public ResponseEntity getAll() {
        return ResponseEntity.ok(sharedAlbumService.findAllSharedAlbums());
    }

    @Override
    public ResponseEntity saveSharedAlbumPermissions(SharedAlbumDTO newSharedAlbum) {
        sharedAlbumService.saveSharedAlbum(newSharedAlbum);
        return ResponseEntity.ok("Permisos de usuario guardados sobre el album con id " + newSharedAlbum.getAlbumId());
    }

    @Override
    public ResponseEntity saveSharedAlbumPermissionsList(List<SharedAlbumDTO> newSharedAlbumList) {
        if(newSharedAlbumList == null || newSharedAlbumList.size() == 0) return ResponseEntity.badRequest().body("No se recibió ningún registro");
        sharedAlbumService.saveSharedAlbumList(newSharedAlbumList);
        return ResponseEntity.ok("Permisos de usuario guardados");
    }

    @Override
    public ResponseEntity getSharedAlbumById(Long id) {
        return ResponseEntity.ok(sharedAlbumService.getSharedAlbumById(id));
    }

    @Override
    public ResponseEntity getUsersFromSharedAlbumByPermissions(Long id, String permission) {
        if (permission == null || (!PermissionsConstants.READ.equals(permission) && !PermissionsConstants.WRITE.equals(permission))) {
            return ResponseEntity.badRequest().body("Los unicos valores validos para los permisos son: read/write");
        }
        return ResponseEntity.ok(sharedAlbumService.getUsersFromSharedAlbumByPermissions(id, permission));
    }
}
