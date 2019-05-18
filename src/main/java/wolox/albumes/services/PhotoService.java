package wolox.albumes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wolox.albumes.clients.PhotoClient;
import wolox.albumes.dtos.PhotoDTO;
import wolox.albumes.models.Photo;
import wolox.albumes.utils.DozerHelper;

import java.util.List;

@Service
public class PhotoService {

    @Autowired
    private PhotoClient photoClient;

    public List<Photo> getPhotos(){
        return DozerHelper.mapList(photoClient.getPhotos(), Photo.class);
    }

}
