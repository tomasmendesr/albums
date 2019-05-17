package wolox.albumes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wolox.albumes.clients.PostClient;
import wolox.albumes.dtos.PostDTO;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostClient postClient;

    public List<PostDTO> getPosts(){
        return postClient.getPosts();
    }

}
