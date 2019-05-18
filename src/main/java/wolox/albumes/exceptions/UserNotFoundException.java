package wolox.albumes.exceptions;

public class UserNotFoundException extends AlbumAppException {
    public UserNotFoundException(Long id) {
        super("No se encontró ningún usuario con el id " + id);
    }

}
