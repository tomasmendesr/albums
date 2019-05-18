package wolox.albumes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import wolox.albumes.clients.*;
import wolox.albumes.dtos.*;
import wolox.albumes.models.*;
import wolox.albumes.repositories.SharedAlbumDataRepository;
import wolox.albumes.services.*;
import wolox.albumes.utils.PermissionsConstants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumesApplicationTests {


	@Autowired
	RestTemplate restTemplate;
	@Autowired
	CommentService commentService;
	@Autowired
	PhotoService photoService;
	@Autowired
	PostService postService;
	@Autowired
	AlbumService albumService;
	@Autowired
	UserService userService;
	@Autowired
	SharedAlbumDataService sharedAlbumDataService;

	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);

		SharedAlbumDTO album1 = new SharedAlbumDTO();
		album1.setAlbumId(1L);
		album1.setUserId(1L);
		album1.setRead(true);
		album1.setWrite(true);

		SharedAlbumDTO album2 = new SharedAlbumDTO();
		album2.setAlbumId(1L);
		album2.setUserId(2L);
		album2.setRead(true);
		album2.setWrite(false);

		SharedAlbumDTO album3 = new SharedAlbumDTO();
		album3.setAlbumId(2L);
		album3.setUserId(3L);
		album3.setRead(true);
		album3.setWrite(false);

		sharedAlbumDataService.saveSharedAlbumList(Arrays.asList(album1, album2));
		sharedAlbumDataService.saveSharedAlbum(album3);
	}

	@Test
	public void testFindAllSharedAlbumsData(){
		// Testeo además el guardado realizado en el @Before
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
	public void testGetComments(){
		List<Comment> commentsFromService = commentService.getComments();
		CommentDTO[] commentsFromExternalService = restTemplate.getForObject(GenericRestClient.EXTERNAL_SERVICE_URL + CommentClient.GET_COMMENTS_REQUEST, CommentDTO[].class);
		Assert.assertEquals(commentsFromExternalService.length, commentsFromService.size(), 0);
	}

	@Test
	public void testGetCommentsWithFilterName() throws UnsupportedEncodingException {
		String nameFilter = "id labore ex et quam laborum";
		List<Comment> comments = commentService.getCommentsApplyingFilters(null, URLEncoder.encode(nameFilter, StandardCharsets.UTF_8.name()));
		Assert.assertEquals(1, comments.size(), 0);
	}

	@Test
	public void testGetCommentsWithFilterUserId() throws UnsupportedEncodingException {
		List<Comment> comments = commentService.getCommentsApplyingFilters(1L, null);
		// No coincide ningun email de los comentarios con los de los usuarios
		Assert.assertEquals(0, comments.size(), 0);
	}

	@Test
	public void testGetPhotos(){
		List<Photo> fromMyService = photoService.getPhotos();
		PhotoDTO[] fromExternalService = restTemplate.getForObject(GenericRestClient.EXTERNAL_SERVICE_URL + PhotoClient.GET_PHOTOS_REQUEST, PhotoDTO[].class);
		Assert.assertEquals(fromExternalService.length, fromMyService.size(), 0);
	}

	@Test
	public void testGetAlbums(){
		List<Album> fromMyService = albumService.getAlbums();
		AlbumDTO[] fromExternalService = restTemplate.getForObject(GenericRestClient.EXTERNAL_SERVICE_URL + AlbumClient.GET_ALBUMES_REQUEST, AlbumDTO[].class);
		Assert.assertEquals(fromExternalService.length, fromMyService.size(), 0);
	}

	@Test
	public void testGetPosts(){
		List<Post> fromMyService = postService.getPosts();
		PostDTO[] fromExternalService = restTemplate.getForObject(GenericRestClient.EXTERNAL_SERVICE_URL + PostClient.GET_POSTS_REQUEST, PostDTO[].class);
		Assert.assertEquals(fromExternalService.length, fromMyService.size(), 0);
	}

	@Test
	public void testGetUsers(){
		List<User> fromMyService = userService.getUsers();
		UserDTO[] fromExternalService = restTemplate.getForObject(GenericRestClient.EXTERNAL_SERVICE_URL + UserClient.GET_USERS_REQUEST, UserDTO[].class);
		Assert.assertEquals(fromExternalService.length, fromMyService.size(), 0);
	}

	@Test
	public void testGetUserById(){
		User user = userService.getUserById(2L);
		Assert.assertTrue(user != null && user.getId() == 2L);
	}

	@Test
	public void testFindSharedAlbumByAlbumAndUserId(){
		SharedAlbumData sharedAlbumData = sharedAlbumDataService.findByAlbumAndUserId(1L, 1L);
		Assert.assertTrue(sharedAlbumData != null && sharedAlbumData.getUserId() != null && sharedAlbumData.getAlbumId() != null);
	}

	@Test
	public void testGetUsersFromSharedAlbumByPermissions(){
		List<User> usersWithReadPermissionsOnAlbumWithId1 = sharedAlbumDataService.getUsersFromSharedAlbumByPermissions(1L, PermissionsConstants.READ);
		Assert.assertEquals(2, usersWithReadPermissionsOnAlbumWithId1.size(), 0);
	}


}
