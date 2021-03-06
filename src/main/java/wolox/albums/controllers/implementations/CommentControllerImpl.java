package wolox.albums.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import wolox.albums.controllers.interfaces.CommentController;
import wolox.albums.services.CommentService;

@RestController
public class CommentControllerImpl implements CommentController {

    @Autowired
    private CommentService commentService;

    @Override
    public ResponseEntity getComments(Long userId, String name) {
        if(userId == null && (!StringUtils.hasText(name))){
            return getAllComments();
        }
        return getCommentsApplyingFilters(userId,name);
    }

    private ResponseEntity getAllComments(){
        return ResponseEntity.ok(commentService.getComments());
    }

    private ResponseEntity getCommentsApplyingFilters(Long userId, String name){
        return ResponseEntity.ok(commentService.getCommentsApplyingFilters(userId, name));
    }
}
