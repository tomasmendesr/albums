package wolox.albumes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wolox.albumes.models.SharedAlbum;
import wolox.albumes.models.SharedAlbumId;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface SharedAlbumRepository extends JpaRepository<SharedAlbum, SharedAlbumId> {
    List<SharedAlbum> findSharedAlbumByAlbumId(Long albumId);
}
