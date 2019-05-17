package wolox.albumes.exceptions;

public class AlbumNotFoundException extends RuntimeException {

    public AlbumNotFoundException(Long id) {
        super("No se encontró ningún album con el id " + id);
    }

}
