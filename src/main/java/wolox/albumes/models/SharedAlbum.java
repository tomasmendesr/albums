package wolox.albumes.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "SharedAlbum")
public class SharedAlbum {

    private SharedAlbumId id;
    private Long albumId;
    private Long userId;
    private Boolean read;
    private Boolean write;

    @EmbeddedId
    public SharedAlbumId getId() {
        return id;
    }

    public void setId(SharedAlbumId id) {
        this.id = id;
    }

    @Column(name = "write")
    public Boolean getWrite() {
        return write;
    }

    public void setWrite(Boolean write) {
        this.write = write;
    }

    @Column(name = "read")
    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    @Column(name = "userId", insertable = false, updatable = false)
    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    @Column(name = "userId", insertable = false, updatable = false)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
