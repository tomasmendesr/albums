package wolox.albumes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wolox.albumes.clients.AlbumClient;
import wolox.albumes.exceptions.AlbumNotFoundException;
import wolox.albumes.models.Album;
import wolox.albumes.utils.DozerHelper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumService {

    @Autowired
    private AlbumClient albumClient;

    public List<Album> getAlbums(){
        return DozerHelper.mapList(albumClient.getAlbums(), Album.class);
    }

    public List<Album> getAlbumsByUserId(Long userId) {
        return DozerHelper.mapList(albumClient.getAlbumsByUserId(userId), Album.class);
    }

    public Album getAlbumById(Long id) {
        return DozerHelper.map(albumClient.getAlbumById(id), Album.class);
    }

    private boolean albumExists(Long albumId){
        return getAlbums().stream().map(Album::getId).collect(Collectors.toList()).contains(albumId);
    }

    public void checkIfAlbumExistsOrThrowAlbumNotFoundException(Long userId){
        if(!albumExists(userId)) throw new AlbumNotFoundException(userId);
    }
}
