package wolox.albumes;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import wolox.albumes.clients.AlbumClient;
import wolox.albumes.dtos.AlbumDTO;
import wolox.albumes.models.SharedAlbum;
import wolox.albumes.models.SharedAlbumId;
import wolox.albumes.repositories.SharedAlbumRepository;
import wolox.albumes.services.SharedAlbumService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumesApplicationTests {

	@Autowired
	SharedAlbumRepository repository;
	@Autowired
	AlbumClient albumClient;
	@Autowired
	SharedAlbumService albumService;

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
		AlbumDTO album = albumService.getSharedAlbumById(1L);
		AlbumDTO album2 = albumClient.getAlbumById(1L);
		Assert.assertEquals(album, album2);
	}
}
