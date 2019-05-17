package wolox.albumes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wolox.albumes.dtos.PhotoDTO;
import wolox.albumes.utils.APP_CONFIG;

import java.util.Arrays;
import java.util.List;

@Service
public class PhotoService {
    public static final String GET_PHOTOS_REQUEST = "/photos";

    @Autowired
    private RestTemplate restTemplate;

    public List<PhotoDTO> getPhotos(){
        return Arrays.asList(restTemplate.getForObject(getPhotosRequest(), PhotoDTO[].class));
    }

    private String getPhotosRequest(){
        return APP_CONFIG.EXTERNAL_SERVICE_URL + GET_PHOTOS_REQUEST;
    }
}
