package ch.hearc.sandbox.repository;

import ch.hearc.sandbox.model.Comment;
import ch.hearc.sandbox.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    //Source : https://stackoverflow.com/questions/32434058/how-to-implement-pagination-in-spring-boot-with-hibernate
    @Query(getAllCommentsDesc)
    List<Comment> getAllCommentsDesc(Pageable pageable);

    String getAllCommentsDesc = "from Comment order by createdDate DESC";
}
