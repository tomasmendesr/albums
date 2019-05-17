package wolox.albumes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wolox.albumes.clients.AlbumClient;
import wolox.albumes.dtos.AlbumDTO;
import wolox.albumes.utils.APP_CONFIG;

import java.util.Arrays;
import java.util.List;

@Service
public class AlbumService {

    @Autowired
    private AlbumClient albumClient;

    public List<AlbumDTO> getAlbumes(){
        return albumClient.getAlbumes();
    }

    public List<AlbumDTO> getAlbumesByUserId(String userId) {
        return albumClient.getAlbumesByUserId(userId);
    }
}
