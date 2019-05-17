package wolox.albumes.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SharedAlbumId implements Serializable {
    @Column(name = "userId")
    private Long userId;

    @Column(name = "albumId")
    private Long albumId;

    public SharedAlbumId() {
    }

    public SharedAlbumId(Long albumId, Long userId) {
        this.albumId = albumId;
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public void setAlbumId(Long albumId){
        this.albumId = albumId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SharedAlbumId)) return false;
        SharedAlbumId that = (SharedAlbumId) o;
        return Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getAlbumId(), that.getAlbumId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getAlbumId());
    }
}
