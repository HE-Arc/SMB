package ch.hearc.sandbox.repository;

import ch.hearc.sandbox.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
