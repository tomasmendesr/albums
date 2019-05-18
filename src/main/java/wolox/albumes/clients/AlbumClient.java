package wolox.albumes.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import wolox.albumes.dtos.AlbumDTO;
import wolox.albumes.utils.APP_CONFIG;

import java.util.Arrays;
import java.util.List;

@Component
public class AlbumClient {
    public static final String GET_ALBUMES_REQUEST = "/albums";

    @Autowired
    private RestTemplate restTemplate;

    public List<AlbumDTO> getAlbums(){
        return Arrays.asList(restTemplate.getForObject(getAlbumesRequest(), AlbumDTO[].class));
    }

    public List<AlbumDTO> getAlbumsByUserId(Long userId) {
        return Arrays.asList(restTemplate.getForObject(getAlbumesByUserIdRequest(userId), AlbumDTO[].class));
    }

    public AlbumDTO getAlbumById(Long albumId) {
        return restTemplate.getForObject(getAlbumByIdRequest(albumId), AlbumDTO.class);
    }

    private String getAlbumesRequest(){
        return APP_CONFIG.EXTERNAL_SERVICE_URL + GET_ALBUMES_REQUEST;
    }

    private String getAlbumesByUserIdRequest(Long userId){
        String userIdFilter = "/" + userId;
        return APP_CONFIG.EXTERNAL_SERVICE_URL + UserClient.GET_USERS_REQUEST + userIdFilter + GET_ALBUMES_REQUEST;
    }

    private String getAlbumByIdRequest(Long albumId){
        return getAlbumesRequest() + "/" + albumId;
    }
}
