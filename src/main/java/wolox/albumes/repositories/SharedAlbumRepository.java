package wolox.albumes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wolox.albumes.models.SharedAlbum;
import wolox.albumes.models.SharedAlbumId;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface SharedAlbumRepository extends JpaRepository<SharedAlbum, SharedAlbumId> {

    @Query("SELECT a FROM SharedAlbum a WHERE a.albumId = ?1") // ?1 = first param in method
    List<SharedAlbum> findSharedAlbumByAlbumId(Long albumId); // params
}
