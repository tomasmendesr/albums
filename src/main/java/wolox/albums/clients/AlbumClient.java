package wolox.albums.clients;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import wolox.albums.dtos.AlbumDTO;

import java.util.Arrays;
import java.util.List;

@Component
public class AlbumClient extends GenericRestClient {
    public static final String GET_ALBUMES_REQUEST = "/albums";

    public AlbumClient(RestTemplateBuilder restTemplateBuilder) {
        super(restTemplateBuilder);
    }

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
        return EXTERNAL_SERVICE_URL + GET_ALBUMES_REQUEST;
    }

    private String getAlbumesByUserIdRequest(Long userId){
        String userIdFilter = "/" + userId;
        return EXTERNAL_SERVICE_URL + UserClient.GET_USERS_REQUEST + userIdFilter + GET_ALBUMES_REQUEST;
    }

    private String getAlbumByIdRequest(Long albumId){
        return getAlbumesRequest() + "/" + albumId;
    }
}
