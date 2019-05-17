package wolox.albumes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wolox.albumes.clients.PhotoClient;
import wolox.albumes.dtos.PhotoDTO;

import java.util.List;

@Service
public class PhotoService {

    @Autowired
    private PhotoClient photoClient;

    public List<PhotoDTO> getPhotos(){
        return photoClient.getPhotos();
    }

}
