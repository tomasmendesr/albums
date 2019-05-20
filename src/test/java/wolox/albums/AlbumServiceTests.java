package wolox.albums;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import wolox.albums.clients.AlbumClient;
import wolox.albums.clients.GenericRestClient;
import wolox.albums.dtos.AlbumDTO;
import wolox.albums.models.Album;
import wolox.albums.services.AlbumService;

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
