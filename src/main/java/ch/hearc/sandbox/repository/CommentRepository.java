package ch.hearc.sandbox.repository;

import ch.hearc.sandbox.model.Comment;
import ch.hearc.sandbox.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
