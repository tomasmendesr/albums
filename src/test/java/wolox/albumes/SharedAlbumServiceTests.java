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
import wolox.albumes.clients.*;
import wolox.albumes.models.*;
import wolox.albumes.repositories.SharedAlbumDataRepository;
import wolox.albumes.services.*;
import wolox.albumes.models.PermissionsConstants;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SharedAlbumServiceTests {

	@Autowired
	RestTemplate restTemplate;
	@Mock
	AlbumService albumService;
	@Mock
	SharedAlbumDataRepository sharedAlbumDataRepository;
	@Mock
	UserService userService;
	@InjectMocks
	SharedAlbumDataService sharedAlbumDataService;

	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);

		Album album = new Album();
		album.setId(1L);
		album.setUserId(1L);
		album.setTitle("quidem molestiae enim");

		Album album2 = new Album();
		album2.setId(2L);
		album2.setUserId(1L);
		album2.setTitle("sunt qui excepturi placeat culpa");

		SharedAlbumData sharedAlbumData1 = new SharedAlbumData();
		sharedAlbumData1.setId(new SharedAlbumDataId(1L, 1L));
		sharedAlbumData1.setAlbumId(1L);
		sharedAlbumData1.setUserId(1L);
		sharedAlbumData1.setRead(true);
		sharedAlbumData1.setWrite(true);

		SharedAlbumData sharedAlbumData2 = new SharedAlbumData();
		sharedAlbumData2.setId(new SharedAlbumDataId(1L,2L));
		sharedAlbumData2.setAlbumId(1L);
		sharedAlbumData2.setUserId(2L);
		sharedAlbumData2.setRead(true);
		sharedAlbumData2.setWrite(false);

		SharedAlbumData sharedAlbumData3 = new SharedAlbumData();
		sharedAlbumData3.setId(new SharedAlbumDataId(2L,3L));
		sharedAlbumData3.setAlbumId(2L);
		sharedAlbumData3.setUserId(3L);
		sharedAlbumData3.setRead(true);
		sharedAlbumData3.setWrite(false);

		User user1 = new User();
		user1.setId(1L);
		user1.setName("Leanne Graham");
		user1.setUsername("Bret");
		user1.setEmail("Sincere@april.biz");

		User user2 = new User();
		user2.setId(2L);
		user2.setName("Ervin Howell");
		user2.setUsername("Antonette");
		user2.setEmail("Shanna@melissa.tv");

		when(albumService.getAlbums()).thenReturn(Arrays.asList(restTemplate.getForObject(AlbumClient.EXTERNAL_SERVICE_URL + AlbumClient.GET_ALBUMES_REQUEST, Album[].class)));
		when(albumService.getAlbumById(any())).thenAnswer(i -> (Long) i.getArguments()[0] == 1L ? album : album2);
		when(sharedAlbumDataRepository.findAll()).thenReturn(Arrays.asList(sharedAlbumData1, sharedAlbumData2, sharedAlbumData3));
		when(sharedAlbumDataRepository.findByAlbumIdAndUserId(any(), any())).thenAnswer(i -> (Long) i.getArguments()[0] == 1L ? sharedAlbumData1 : sharedAlbumData2);
		when(sharedAlbumDataRepository.findSharedAlbumDataByAlbumId(any())).thenReturn(Arrays.asList(sharedAlbumData1, sharedAlbumData2));
		when(userService.getUserById(any())).thenAnswer(i -> (Long) i.getArguments()[0] == 1L ? user1 : user2);
	}

	@Test
	public void testFindAllSharedAlbumsData(){
		List<SharedAlbumData> data = sharedAlbumDataService.findAll();
		Assert.assertEquals(3, data.size(), 0);
	}

	@Test
	public void testFindAllSharedAlbums(){
		List<Album> albums = sharedAlbumDataService.findAllSharedAlbums();
		Assert.assertEquals(2, albums.size(), 0); // Son 2 porque se guardaron registros sobre los albumes con id 1 y 2.
	}

	@Test
	public void testGetSharedAlbumById(){
		Album album = sharedAlbumDataService.getSharedAlbumById(1L);
		Assert.assertTrue(album != null && album.getId() == 1L);
	}

	@Test
	public void testFindSharedAlbumByAlbumAndUserId(){
		SharedAlbumData sharedAlbumData = sharedAlbumDataService.findByAlbumAndUserId(1L, 1L);
		Assert.assertTrue(sharedAlbumData != null && sharedAlbumData.getUserId() != null && sharedAlbumData.getAlbumId() != null);
	}

	@Test
	public void testGetUsersFromSharedAlbumWithReadPermissions(){
		List<User> usersWithReadPermissionsOnAlbumWithId1 = sharedAlbumDataService.getUsersFromSharedAlbumByPermissions(1L, PermissionsConstants.READ);
		Assert.assertEquals(2, usersWithReadPermissionsOnAlbumWithId1.size(), 0);
	}

	@Test
	public void testGetUsersFromSharedAlbumWithWritePermissions(){
		List<User> usersWithWritePermissionsOnAlbumWithId1 = sharedAlbumDataService.getUsersFromSharedAlbumByPermissions(1L, PermissionsConstants.WRITE);
		Assert.assertEquals(1, usersWithWritePermissionsOnAlbumWithId1.size(), 0);
	}


}
