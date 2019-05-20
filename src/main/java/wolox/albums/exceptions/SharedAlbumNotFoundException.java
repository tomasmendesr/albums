package wolox.albums.exceptions;

public class SharedAlbumNotFoundException extends AlbumAppException {

    public SharedAlbumNotFoundException(Long id) {
        super("No se encontró ningún album con el id " + id);
    }
}
