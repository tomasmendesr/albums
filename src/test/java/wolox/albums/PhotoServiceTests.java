package wolox.albums;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import wolox.albums.clients.GenericRestClient;
import wolox.albums.clients.PhotoClient;
import wolox.albums.dtos.PhotoDTO;
import wolox.albums.models.Photo;
import wolox.albums.services.PhotoService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhotoServiceTests {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    PhotoService photoService;

    @Test
    public void testGetPhotos(){
        List<Photo> fromMyService = photoService.getPhotos();
        PhotoDTO[] fromExternalService = restTemplate.getForObject(GenericRestClient.EXTERNAL_SERVICE_URL + PhotoClient.GET_PHOTOS_REQUEST, PhotoDTO[].class);
        Assert.assertEquals(fromExternalService.length, fromMyService.size(), 0);
    }

}
