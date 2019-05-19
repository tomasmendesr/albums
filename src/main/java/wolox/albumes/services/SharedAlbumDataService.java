package wolox.albumes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wolox.albumes.dtos.SharedAlbumDataDTO;
import wolox.albumes.exceptions.SharedAlbumNotFoundException;
import wolox.albumes.models.Album;
import wolox.albumes.models.SharedAlbumData;
import wolox.albumes.models.SharedAlbumDataId;
import wolox.albumes.models.User;
import wolox.albumes.repositories.SharedAlbumDataRepository;
import wolox.albumes.models.PermissionsConstants;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SharedAlbumDataService {

    private SharedAlbumDataRepository repository;
    private AlbumService albumService;
    private UserService userService;

    @Autowired
    SharedAlbumDataService(SharedAlbumDataRepository repository, UserService userService, AlbumService albumService){
        this.repository = repository;
        this.userService = userService;
        this.albumService = albumService;
    }

    /* Return all sharedAlbumData*/
    public List<SharedAlbumData> findAll(){
        return repository.findAll();
    }

    /* Return all albums that contains sharedAlbumData */
    public List<Album> findAllSharedAlbums() {
        List<Long> idsSharedAlbums = getSharedAlbumsIds();
        return albumService.getAlbums().stream().filter(a -> idsSharedAlbums.contains(a.getId())).collect(Collectors.toList());
    }

    private List<Long> getSharedAlbumsIds(){
        return repository.findAll().stream().map(s -> s.getAlbumId()).distinct().collect(Collectors.toList());
    }

    public void saveSharedAlbum(SharedAlbumDataDTO newSharedAlbum) {
        albumService.checkIfAlbumExistsOrThrowAlbumNotFoundException(newSharedAlbum.getAlbumId());
        save(dtoToModel(newSharedAlbum));
    }

    public Album getSharedAlbumById(Long id)  {
        if(findSharedAlbumsDataByAlbumId(id).size() == 0) throw new SharedAlbumNotFoundException(id);
        return albumService.getAlbumById(id);
    }

    public void saveSharedAlbumList(List<SharedAlbumDataDTO> newSharedAlbumList) {
        albumService.checkIfAlbumExistsOrThrowAlbumNotFoundException(newSharedAlbumList.get(0).getAlbumId());
        newSharedAlbumList.forEach(dto -> save(dtoToModel(dto)));
    }

    public List<User> getUsersFromSharedAlbumByPermissions(Long albumId, String permission) {
        List<User> users = new ArrayList<>();
        List<Long> usersIds = findSharedAlbumsDataByAlbumId(albumId).stream()
                .filter(sharedAlbumData -> PermissionsConstants.WRITE.equals(permission) ? sharedAlbumData.getWrite() : sharedAlbumData.getRead())
                .map(sharedAlbumData -> sharedAlbumData.getUserId())
                .distinct()
                .collect(Collectors.toList());
        usersIds.forEach(userId -> users.add(userService.getUserById(userId)));
        return users;
    }

    private void save(SharedAlbumData album){
        repository.save(album);
    }

    public SharedAlbumData findByAlbumAndUserId(Long albumId, Long userId){
        return repository.findByAlbumIdAndUserId(albumId, userId);
    }

    /********* UTILS *****************/
    private SharedAlbumData dtoToModel(SharedAlbumDataDTO dto){
        SharedAlbumData album = new SharedAlbumData();
        album.setId(new SharedAlbumDataId(dto.getAlbumId(), dto.getUserId()));
        album.setRead(dto.getRead());
        album.setWrite(dto.getWrite());
        return album;
    }

    private List<SharedAlbumData> findSharedAlbumsDataByAlbumId(Long id){
        return repository.findSharedAlbumDataByAlbumId(id);
    }

}
