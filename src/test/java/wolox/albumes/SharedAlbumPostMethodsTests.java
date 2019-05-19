package wolox.albumes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
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
import wolox.albumes.dtos.SharedAlbumDataDTO;
import wolox.albumes.exceptions.InvalidSharedAlbumObjectException;
import wolox.albumes.repositories.SharedAlbumDataRepository;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SharedAlbumPostMethodsTests {

    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    MockMvc mockMvc;
    @Autowired
    SharedAlbumDataRepository sharedAlbumDataRepository;

    @After
    public void clean(){
        sharedAlbumDataRepository.deleteAll();
    }

    @Test
    public void testSaveSharedAlbumDataPostRequest() throws Exception {
        SharedAlbumDataDTO newData = new SharedAlbumDataDTO();
        newData.setAlbumId(4L);
        newData.setUserId(4L);
        newData.setRead(false);
        newData.setWrite(true);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/sharedAlbums/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newData))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.OK.value());
    }

    @Test
    public void testSaveSharedAlbumDataGenerateExceptionPostRequest() throws Exception {
        SharedAlbumDataDTO newData = new SharedAlbumDataDTO();
        newData.setAlbumId(1L);
        newData.setRead(false);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/sharedAlbums/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newData))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value());
        Assert.assertTrue(result.getResolvedException() instanceof InvalidSharedAlbumObjectException);
    }

    @Test
    public void testSaveSharedAlbumDataListGenerateExceptionPostRequest() throws Exception {
        SharedAlbumDataDTO newData = new SharedAlbumDataDTO();
        newData.setUserId(1L);
        newData.setAlbumId(2L);
        newData.setWrite(false);
        newData.setRead(true);

        SharedAlbumDataDTO newData2 = new SharedAlbumDataDTO();
        newData2.setUserId(1L);
        newData2.setWrite(false);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/sharedAlbums/saveList")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(Arrays.asList(newData, newData2)))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value());
        Assert.assertTrue(result.getResolvedException() instanceof InvalidSharedAlbumObjectException);
    }

    @Test
    public void testSaveSharedAlbumDataLisPostRequest() throws Exception {
        SharedAlbumDataDTO newData = new SharedAlbumDataDTO();
        newData.setUserId(1L);
        newData.setAlbumId(2L);
        newData.setWrite(false);
        newData.setRead(true);

        SharedAlbumDataDTO newData2 = new SharedAlbumDataDTO();
        newData2.setUserId(1L);
        newData2.setWrite(false);
        newData2.setAlbumId(2L);
        newData2.setRead(false);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/sharedAlbums/saveList")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(Arrays.asList(newData, newData2)))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.OK.value());
    }
}
