package wolox.albums.exceptions;

public class InvalidPermissionException extends AlbumAppException {
    public InvalidPermissionException(String permission) {
        super("Los unicos valores validos para los permisos son: read/write. Valor recibido: " + permission);
    }
}
