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

import java.util.List;

@Service
public class SharedAlbumService {

    private final SharedAlbumRepository repository;
    private AlbumClient albumClient;

    @Autowired
    SharedAlbumService(SharedAlbumRepository repository, AlbumClient albumClient){
        this.repository = repository;
        this.albumClient = albumClient;
    }

    public List<SharedAlbum> findAll() {
        return repository.findAll();
    }

    public void newSharedAlbum(SharedAlbumDTO newSharedAlbum) throws AlbumNotFoundException{
        AlbumDTO albumDTO = albumClient.getAlbumById(newSharedAlbum.getAlbumId());
        if(!isAValidAlbum(albumDTO)) throw new AlbumNotFoundException(newSharedAlbum.getAlbumId());
        repository.save(dtoToModel(newSharedAlbum));
    }

    public AlbumDTO getSharedAlbumById(Long id) throws SharedAlbumNotFoundException {
        List<SharedAlbum> sharedAlbums = repository.findSharedAlbumByAlbumId(id);
        if(sharedAlbums.size() == 0) throw new SharedAlbumNotFoundException(id);
        return albumClient.getAlbumById(id);
    }

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
}
