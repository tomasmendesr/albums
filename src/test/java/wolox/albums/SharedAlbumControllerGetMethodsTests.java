package wolox.albums;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import wolox.albums.exceptions.InvalidPermissionException;
import wolox.albums.models.SharedAlbumData;
import wolox.albums.models.SharedAlbumDataId;
import wolox.albums.repositories.SharedAlbumDataRepository;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SharedAlbumControllerGetMethodsTests {

    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    MockMvc mockMvc;
    @Autowired
    SharedAlbumDataRepository sharedAlbumDataRepository;

    @Before
    public void setUp(){
        SharedAlbumData sharedAlbumData1 = new SharedAlbumData();
        sharedAlbumData1.setId(new SharedAlbumDataId(1L, 1L));
        sharedAlbumData1.setRead(true);
        sharedAlbumData1.setWrite(true);

        SharedAlbumData sharedAlbumData2 = new SharedAlbumData();
        sharedAlbumData2.setId(new SharedAlbumDataId(1L,2L));
        sharedAlbumData2.setRead(true);
        sharedAlbumData2.setWrite(false);

        SharedAlbumData sharedAlbumData3 = new SharedAlbumData();
        sharedAlbumData3.setId(new SharedAlbumDataId(2L,3L));
        sharedAlbumData3.setRead(true);
        sharedAlbumData3.setWrite(false);

        sharedAlbumDataRepository.saveAll(Arrays.asList(sharedAlbumData1, sharedAlbumData2, sharedAlbumData3));
    }

    @After
    public void clean(){
        sharedAlbumDataRepository.deleteAll();
    }

    @Test
    public void testGetSharedAlbumsRequest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/sharedAlbums")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        Object[] response = mapper.readValue(result.getResponse().getContentAsString(), Object[].class);
        Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.OK.value());
        Assert.assertEquals(2, response.length, 0); // los albums compartidos son los que tienen id 1 y 2
    }

    @Test
    public void testGetSharedAlbumsDataRequest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/sharedAlbumsData")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        Object[] response = mapper.readValue(result.getResponse().getContentAsString(), Object[].class);
        Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.OK.value());
        Assert.assertEquals(3, response.length, 0); // el album 1 tiene dos usuarios
    }


    @Test
    public void testGetSharedAlbumByIdRequest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/sharedAlbums/1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.OK.value());
    }

    @Test
    public void testGetUsersWithReadPermissionOnEspecificAlbumRequest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/sharedAlbums/1/read")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        Object[] response = mapper.readValue(result.getResponse().getContentAsString(), Object[].class);
        Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.OK.value());
        Assert.assertEquals(2, response.length, 0); // el album 1 tiene dos usuarios
    }

    @Test
    public void testGetUsersWithWritePermissionOnEspecificAlbumRequest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/sharedAlbums/1/write")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        Object[] response = mapper.readValue(result.getResponse().getContentAsString(), Object[].class);
        Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.OK.value());
        Assert.assertEquals(1, response.length, 0); // el album 1 tiene dos usuarios
    }

    @Test
    public void testGetUsersWithWrongPermissionOnEspecificAlbumRequest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/sharedAlbums/1/error")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value());
        Assert.assertTrue(result.getResolvedException() instanceof InvalidPermissionException);
    }

}
