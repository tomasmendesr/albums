package wolox.albumes.exceptions;

public class SharedAlbumNotFoundException extends RuntimeException {

    public SharedAlbumNotFoundException(Long id) {
        super("No se encontró ningún album con el id " + id);
    }
}
