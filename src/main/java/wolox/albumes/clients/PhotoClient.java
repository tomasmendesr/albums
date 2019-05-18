package wolox.albumes.clients;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import wolox.albumes.dtos.PhotoDTO;

import java.util.Arrays;
import java.util.List;

@Component
public class PhotoClient extends GenericRestClient {
    public static final String GET_PHOTOS_REQUEST = "/photos";

    public PhotoClient(RestTemplateBuilder restTemplateBuilder) {
        super(restTemplateBuilder);
    }

    public List<PhotoDTO> getPhotos(){
        return Arrays.asList(restTemplate.getForObject(getPhotosRequest(), PhotoDTO[].class));
    }

    private String getPhotosRequest(){
        return EXTERNAL_SERVICE_URL + GET_PHOTOS_REQUEST;
    }
}
