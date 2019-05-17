package wolox.albumes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wolox.albumes.clients.AlbumClient;
import wolox.albumes.dtos.AlbumDTO;

import java.util.List;

@Service
public class AlbumService {

    @Autowired
    private AlbumClient albumClient;

    public List<AlbumDTO> getAlbums(){
        return albumClient.getAlbums();
    }

    public List<AlbumDTO> getAlbumsByUserId(String userId) {
        return albumClient.getAlbumsByUserId(userId);
    }
}
