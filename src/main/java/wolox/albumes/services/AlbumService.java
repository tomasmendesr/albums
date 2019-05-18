package wolox.albumes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wolox.albumes.clients.AlbumClient;
import wolox.albumes.models.Album;
import wolox.albumes.utils.DozerHelper;

import java.util.List;

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
}
