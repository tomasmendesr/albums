package wolox.albumes.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wolox.albumes.controllers.interfaces.SharedAlbumController;
import wolox.albumes.dtos.SharedAlbumDTO;
import wolox.albumes.exceptions.SharedAlbumNotFoundException;
import wolox.albumes.services.SharedAlbumService;

@RestController
public class SharedAlbumControllerImpl implements SharedAlbumController {

    @Autowired
    private SharedAlbumService sharedAlbumService;

    @Override
    public ResponseEntity getAll() {
        return ResponseEntity.ok(sharedAlbumService.findAll());
    }

    @Override
    public ResponseEntity newSharedAlbum(SharedAlbumDTO newSharedAlbum) {
        try {
            sharedAlbumService.newSharedAlbum(newSharedAlbum);
            return ResponseEntity.ok("Album guardado");
        } catch (Exception e){
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
