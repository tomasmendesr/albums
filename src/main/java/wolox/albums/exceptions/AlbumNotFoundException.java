package wolox.albums.exceptions;

public class AlbumNotFoundException extends AlbumAppException {

    public AlbumNotFoundException(Long id) {
        super("No se encontró ningún album con el id " + id);
    }

}
