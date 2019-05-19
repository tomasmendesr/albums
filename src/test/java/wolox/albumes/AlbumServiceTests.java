package wolox.albumes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import wolox.albumes.clients.AlbumClient;
import wolox.albumes.clients.GenericRestClient;
import wolox.albumes.dtos.AlbumDTO;
import wolox.albumes.models.Album;
import wolox.albumes.services.AlbumService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumServiceTests {

//    @Mock
//    AlbumClient albumClient;
    @Autowired
    AlbumService albumService;
    @Autowired
    RestTemplate restTemplate;

//    @Before
//    public void init(){
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void testGetAlbums(){
        List<Album> fromMyService = albumService.getAlbums();
        AlbumDTO[] fromExternalService = restTemplate.getForObject(GenericRestClient.EXTERNAL_SERVICE_URL + AlbumClient.GET_ALBUMES_REQUEST, AlbumDTO[].class);
        Assert.assertEquals(fromExternalService.length, fromMyService.size(), 0);
    }

    @Test
    public void testGetAlbumById(){
        Album fromMyService = albumService.getAlbumById(1L);
        Assert.assertTrue(fromMyService != null && fromMyService.getId() != null && fromMyService.getId() == 1L);
    }

    @Test
    public void testGetAlbumsByUserId(){
        List<Album> fromMyService = albumService.getAlbumsByUserId(1L);
        Assert.assertTrue(fromMyService != null && fromMyService.size() > 0);
    }
}
