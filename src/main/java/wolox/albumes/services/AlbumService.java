package wolox.albumes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wolox.albumes.dtos.AlbumDTO;
import wolox.albumes.utils.APP_CONFIG;

import java.util.Arrays;
import java.util.List;

@Service
public class AlbumService {
    public static final String GET_ALBUMES_REQUEST = "/albums";

    @Autowired
    private RestTemplate restTemplate;

    public List<AlbumDTO> getAlbumes(){
        return Arrays.asList(restTemplate.getForObject(getAlbumesRequest(), AlbumDTO[].class));
    }

    public List<AlbumDTO> getAlbumesByUserId(String userId) {
        return Arrays.asList(restTemplate.getForObject(getAlbumesByUserIdRequest(userId), AlbumDTO[].class));
    }

    private String getAlbumesRequest(){
        return APP_CONFIG.EXTERNAL_SERVICE_URL + GET_ALBUMES_REQUEST;
    }

    private String getAlbumesByUserIdRequest(String userId){
        String userIdFilter = "/" + userId;
        return APP_CONFIG.EXTERNAL_SERVICE_URL + UserService.GET_USERS_REQUEST + userIdFilter + GET_ALBUMES_REQUEST;
    }


}
