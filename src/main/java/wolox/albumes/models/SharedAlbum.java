package wolox.albumes.models;

import java.util.List;

public class SharedAlbum {
    private Integer albumId;
    private Integer userOwnerId;
    private List<UserPermissions> userPermissionsList;

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getUserOwnerId() {
        return userOwnerId;
    }

    public void setUserOwnerId(Integer userOwnerId) {
        this.userOwnerId = userOwnerId;
    }

    public List<UserPermissions> getUserPermissionsList() {
        return userPermissionsList;
    }

    public void setUserPermissionsList(List<UserPermissions> userPermissionsList) {
        this.userPermissionsList = userPermissionsList;
    }
}
