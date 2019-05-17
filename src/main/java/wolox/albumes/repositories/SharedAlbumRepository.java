package wolox.albumes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wolox.albumes.models.SharedAlbum;
import wolox.albumes.models.SharedAlbumId;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface SharedAlbumRepository extends JpaRepository<SharedAlbum, SharedAlbumId> {
    List<SharedAlbum> findSharedAlbumByAlbumId(Long albumId);
}
