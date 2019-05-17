package wolox.albumes.models;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@Entity
public class SharedAlbum {
    @EmbeddedId
    private SharedAlbumId id;
    private Boolean read;
    private Boolean write;

    public Boolean getWrite() {
        return write;
    }

    public void setWrite(Boolean write) {
        this.write = write;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public SharedAlbumId getId() {
        return id;
    }

    public void setId(SharedAlbumId id) {
        this.id = id;
    }
}
