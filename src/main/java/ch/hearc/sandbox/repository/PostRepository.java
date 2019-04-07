package ch.hearc.sandbox.repository;

import ch.hearc.sandbox.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    Page<Post> findByBoardIdOrderByModifiedDateDesc(Long boardId, Pageable pageable);
}
