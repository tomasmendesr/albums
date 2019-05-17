package wolox.albumes.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import wolox.albumes.dtos.PhotoDTO;
import wolox.albumes.utils.APP_CONFIG;

import java.util.Arrays;
import java.util.List;

@Component
public class PhotoClient {
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
