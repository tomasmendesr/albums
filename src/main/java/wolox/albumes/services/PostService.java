package wolox.albumes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wolox.albumes.clients.PostClient;
import wolox.albumes.dtos.PostDTO;
import wolox.albumes.models.Post;
import wolox.albumes.utils.DozerHelper;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostClient postClient;

    public List<Post> getPosts(){
        return DozerHelper.mapList(postClient.getPosts(), Post.class);
    }

}
