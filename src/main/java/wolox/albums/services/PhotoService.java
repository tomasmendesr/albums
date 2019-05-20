package wolox.albums.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wolox.albums.clients.PhotoClient;
import wolox.albums.models.Photo;
import wolox.albums.utils.DozerHelper;

import java.util.List;

@Service
public class PhotoService {

    @Autowired
    private PhotoClient photoClient;

    public List<Photo> getPhotos(){
        return DozerHelper.mapList(photoClient.getPhotos(), Photo.class);
    }

}
