package wolox.albumes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wolox.albumes.clients.PhotoClient;
import wolox.albumes.clients.PostClient;
import wolox.albumes.dtos.PhotoDTO;
import wolox.albumes.utils.APP_CONFIG;

import java.util.Arrays;
import java.util.List;

@Service
public class PhotoService {

    @Autowired
    private PhotoClient photoClient;

    public List<PhotoDTO> getPhotos(){
        return photoClient.getPhotos();
    }

}
