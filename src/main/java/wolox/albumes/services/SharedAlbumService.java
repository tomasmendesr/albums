package wolox.albumes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wolox.albumes.clients.AlbumClient;
import wolox.albumes.dtos.AlbumDTO;
import wolox.albumes.dtos.SharedAlbumDTO;
import wolox.albumes.exceptions.AlbumNotFoundException;
import wolox.albumes.exceptions.SharedAlbumNotFoundException;
import wolox.albumes.models.SharedAlbum;
import wolox.albumes.models.SharedAlbumId;
import wolox.albumes.repositories.SharedAlbumRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SharedAlbumService {

    private SharedAlbumRepository repository;
    private AlbumClient albumClient;

    @Autowired
    SharedAlbumService(SharedAlbumRepository repository, AlbumClient albumClient){
        this.repository = repository;
        this.albumClient = albumClient;
    }

    public List<AlbumDTO> findAllSharedAlbums() {
        List<Long> idsSharedAlbums = repository.findAll().stream().map(s -> s.getId().getAlbumId()).collect(Collectors.toList());
        List<AlbumDTO> allAlbums = albumClient.getAlbums();
        return allAlbums.stream().filter(a -> idsSharedAlbums.contains(a.getId())).collect(Collectors.toList());
    }

    public void saveSharedAlbum(SharedAlbumDTO newSharedAlbum) throws AlbumNotFoundException{
        checkAlbumNotFoundException(newSharedAlbum.getAlbumId());
        repository.save(dtoToModel(newSharedAlbum));
    }

    public AlbumDTO getSharedAlbumById(Long id) throws SharedAlbumNotFoundException {
        List<SharedAlbum> sharedAlbums = repository.findSharedAlbumByAlbumId(id);
        if(sharedAlbums.size() == 0) throw new SharedAlbumNotFoundException(id);
        return albumClient.getAlbumById(id);
    }

    public void saveSharedAlbumList(List<SharedAlbumDTO> newSharedAlbumList) throws AlbumNotFoundException {
        checkAlbumNotFoundException(newSharedAlbumList.get(0).getAlbumId());
        newSharedAlbumList.forEach(dto -> repository.save(dtoToModel(dto)));
    }

    /********* UTILS *****************/
    private SharedAlbum dtoToModel(SharedAlbumDTO dto){
        SharedAlbum album = new SharedAlbum();
        album.setId(new SharedAlbumId(dto.getAlbumId(), dto.getUserId()));
        album.setRead(dto.getRead());
        album.setWrite(dto.getWrite());
        return album;
    }

    private boolean isAValidAlbum(AlbumDTO albumDTO){
        return albumDTO.getId() != null;
    }

    public void checkAlbumNotFoundException(Long id) throws AlbumNotFoundException {
        AlbumDTO albumDTO = albumClient.getAlbumById(id);
        if(!isAValidAlbum(albumDTO)) throw new AlbumNotFoundException(id);
    }
}
