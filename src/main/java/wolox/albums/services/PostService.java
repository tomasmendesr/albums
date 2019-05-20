package wolox.albums.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wolox.albums.clients.PostClient;
import wolox.albums.models.Post;
import wolox.albums.utils.DozerHelper;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostClient postClient;

    public List<Post> getPosts(){
        return DozerHelper.mapList(postClient.getPosts(), Post.class);
    }

}
