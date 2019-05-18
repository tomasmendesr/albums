package wolox.albumes.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import wolox.albumes.controllers.interfaces.CommentController;
import wolox.albumes.services.CommentService;

import java.io.UnsupportedEncodingException;

@RestController
public class CommentControllerImpl implements CommentController {

    @Autowired
    private CommentService commentService;

    @Override
    public ResponseEntity getComments(Long userId, String name) {
        try {
            if(userId == null && (name == null || !StringUtils.hasText(name))){
                return ResponseEntity.ok(commentService.getComments());
            }
            return ResponseEntity.ok(commentService.getCommentsApplyingFilters(userId, name));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al leer el parametro 'name'");
        }
    }
}
