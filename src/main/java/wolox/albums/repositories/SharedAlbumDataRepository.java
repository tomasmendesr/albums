package wolox.albums.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wolox.albums.models.SharedAlbumData;
import wolox.albums.models.SharedAlbumDataId;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface SharedAlbumDataRepository extends JpaRepository<SharedAlbumData, SharedAlbumDataId> {
    List<SharedAlbumData> findSharedAlbumDataByAlbumId(Long albumId);

    SharedAlbumData findByAlbumIdAndUserId(Long albumId, Long userId);
}
