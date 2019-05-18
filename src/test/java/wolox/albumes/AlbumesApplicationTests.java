package wolox.albumes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import wolox.albumes.clients.*;
import wolox.albumes.dtos.*;
import wolox.albumes.models.SharedAlbum;
import wolox.albumes.models.SharedAlbumId;
import wolox.albumes.repositories.SharedAlbumRepository;
import wolox.albumes.services.*;
import wolox.albumes.utils.APP_CONFIG;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumesApplicationTests {

	@Autowired
	SharedAlbumRepository repository;
	@Autowired
	AlbumClient albumClient;
	@Autowired
	SharedAlbumService sharedAlbumService;
	@Autowired
	CommentService commentService;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	PhotoService photoService;
	@Autowired
	PostService postService;
	@Autowired
	AlbumService albumService;
	@Autowired
	UserService userService;

	@Before
	public void mockBBDD(){
		SharedAlbum album = new SharedAlbum();
		album.setId(new SharedAlbumId(1L ,1L));
		album.setRead(true);
		album.setUserId(1L);
		album.setAlbumId(1L);
		album.setWrite(false);
		repository.save(album);
	}

	@Test
	public void getSharedAlbumById(){
		AlbumDTO album = sharedAlbumService.getSharedAlbumById(1L);
		Assert.assertTrue(album != null && album.getId() == 1L);
	}

	@Test
	public void testGetComments(){
		List<CommentDTO> commentsFromService = commentService.getComments();
		CommentDTO[] commentsFromExternalService = restTemplate.getForObject(APP_CONFIG.EXTERNAL_SERVICE_URL + CommentClient.GET_COMMENTS_REQUEST, CommentDTO[].class);
		Assert.assertEquals(commentsFromExternalService.length, commentsFromService.size(), 0);
	}

	@Test
	public void testGetCommentsWithFilterName() throws UnsupportedEncodingException {
		String nameFilter = "id labore ex et quam laborum";
		List<CommentDTO> comments = commentService.getCommentsApplyingFilters(null, URLEncoder.encode(nameFilter, StandardCharsets.UTF_8.name()));
		Assert.assertEquals(1, comments.size(), 0);
	}

	@Test
	public void testGetCommentsWithFilterUserId() throws UnsupportedEncodingException {
		List<CommentDTO> comments = commentService.getCommentsApplyingFilters(1L, null);
		// No coincide ningun email de los comentarios con los de los usuarios
		Assert.assertEquals(0, comments.size(), 0);
	}

	@Test
	public void testGetPhotos(){
		List<PhotoDTO> fromMyService = photoService.getPhotos();
		PhotoDTO[] fromExternalService = restTemplate.getForObject(APP_CONFIG.EXTERNAL_SERVICE_URL + PhotoClient.GET_PHOTOS_REQUEST, PhotoDTO[].class);
		Assert.assertEquals(fromExternalService.length, fromMyService.size(), 0);
	}

	@Test
	public void testGetAlbums(){
		List<AlbumDTO> fromMyService = albumService.getAlbums();
		AlbumDTO[] fromExternalService = restTemplate.getForObject(APP_CONFIG.EXTERNAL_SERVICE_URL + AlbumClient.GET_ALBUMES_REQUEST, AlbumDTO[].class);
		Assert.assertEquals(fromExternalService.length, fromMyService.size(), 0);
	}

	@Test
	public void testGetPosts(){
		List<PostDTO> fromMyService = postService.getPosts();
		PostDTO[] fromExternalService = restTemplate.getForObject(APP_CONFIG.EXTERNAL_SERVICE_URL + PostClient.GET_POSTS_REQUEST, PostDTO[].class);
		Assert.assertEquals(fromExternalService.length, fromMyService.size(), 0);
	}

	@Test
	public void testGetUsers(){
		List<UserDTO> fromMyService = userService.getUsers();
		UserDTO[] fromExternalService = restTemplate.getForObject(APP_CONFIG.EXTERNAL_SERVICE_URL + UserClient.GET_USERS_REQUEST, UserDTO[].class);
		Assert.assertEquals(fromExternalService.length, fromMyService.size(), 0);
	}

	@Test
	public void testGetUserById(){
		UserDTO user = userService.getUserById(2L);
		Assert.assertTrue(user != null && user.getId() == 2L);
	}


}
