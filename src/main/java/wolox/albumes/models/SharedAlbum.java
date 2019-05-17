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
}
