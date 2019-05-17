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
        try {
            sharedAlbumService.saveSharedAlbum(newSharedAlbum);
            return ResponseEntity.ok("Album guardado");
        } catch (AlbumNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity saveSharedAlbumPermissionsList(List<SharedAlbumDTO> newSharedAlbumList) {
        try {
            if(newSharedAlbumList == null || newSharedAlbumList.size() == 0) return ResponseEntity.badRequest().body("No se recibió ningún registro");
            sharedAlbumService.saveSharedAlbumList(newSharedAlbumList);
            return ResponseEntity.ok("Lista guardada");
        } catch (AlbumNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity getSharedAlbumById(Long id) {
        try {
            return ResponseEntity.ok(sharedAlbumService.getSharedAlbumById(id));
        } catch (SharedAlbumNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (NumberFormatException e){
            return ResponseEntity.badRequest().body("El id del album debe ser un valor numerico");
        }
    }
}
