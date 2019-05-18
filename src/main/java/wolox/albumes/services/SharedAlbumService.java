package wolox.albumes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wolox.albumes.clients.AlbumClient;
import wolox.albumes.dtos.AlbumDTO;
import wolox.albumes.dtos.SharedAlbumDTO;
import wolox.albumes.dtos.UserDTO;
import wolox.albumes.exceptions.AlbumNotFoundException;
import wolox.albumes.exceptions.SharedAlbumNotFoundException;
import wolox.albumes.models.SharedAlbum;
import wolox.albumes.models.SharedAlbumId;
import wolox.albumes.repositories.SharedAlbumRepository;
import wolox.albumes.utils.PermissionsConstants;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SharedAlbumService {

    private SharedAlbumRepository repository;
    private AlbumClient albumClient;
    private UserService userService;

    @Autowired
    SharedAlbumService(SharedAlbumRepository repository, AlbumClient albumClient, UserService userService){
        this.repository = repository;
        this.albumClient = albumClient;
        this.userService = userService;
    }

    public List<AlbumDTO> findAllSharedAlbums() {
        List<Long> idsSharedAlbums = repository.findAll().stream().map(s -> s.getId().getAlbumId()).distinct().collect(Collectors.toList());
        return albumClient.getAlbums().stream().filter(a -> idsSharedAlbums.contains(a.getId())).collect(Collectors.toList());
    }

    public void saveSharedAlbum(SharedAlbumDTO newSharedAlbum) throws AlbumNotFoundException {
        checkAlbumNotFoundException(newSharedAlbum.getAlbumId());
        repository.save(dtoToModel(newSharedAlbum));
    }

    public AlbumDTO getSharedAlbumById(Long id) throws SharedAlbumNotFoundException {
        if(findSharedAlbumsByAlbumId(id).size() == 0) throw new SharedAlbumNotFoundException(id);
        return albumClient.getAlbumById(id);
    }


    public void saveSharedAlbumList(List<SharedAlbumDTO> newSharedAlbumList) throws AlbumNotFoundException {
        checkAlbumNotFoundException(newSharedAlbumList.get(0).getAlbumId());
        newSharedAlbumList.forEach(dto -> repository.save(dtoToModel(dto)));
    }

    public List<UserDTO> getUsersFromSharedAlbumByPermissions(Long id, String permission) throws SharedAlbumNotFoundException {
        List<UserDTO> users = new ArrayList<>();
        List<Long> usersIds = findSharedAlbumsByAlbumId(id).stream()
                .filter(sharedAlbum -> PermissionsConstants.WRITE.equals(permission) ? sharedAlbum.getWrite() : sharedAlbum.getRead())
                .map(sharedAlbum -> sharedAlbum.getUserId())
                .distinct()
                .collect(Collectors.toList());
        usersIds.forEach(userId -> users.add(userService.getUserById(userId)));
        return users;
    }

    /********* UTILS *****************/
    private SharedAlbum dtoToModel(SharedAlbumDTO dto){
        SharedAlbum album = new SharedAlbum();
        album.setId(new SharedAlbumId(dto.getAlbumId(), dto.getUserId()));
        album.setRead(dto.getRead());
        album.setWrite(dto.getWrite());
        return album;
    }

    private List<SharedAlbum> findSharedAlbumsByAlbumId(Long id){
        return repository.findSharedAlbumByAlbumId(id);
    }

    private boolean isAValidAlbum(AlbumDTO albumDTO){
        return albumDTO.getId() != null;
    }

    public void checkAlbumNotFoundException(Long id) throws AlbumNotFoundException {
        AlbumDTO albumDTO = albumClient.getAlbumById(id);
        if(!isAValidAlbum(albumDTO)) throw new AlbumNotFoundException(id);
    }

}
