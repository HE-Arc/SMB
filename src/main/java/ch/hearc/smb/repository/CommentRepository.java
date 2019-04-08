package ch.hearc.smb.repository;

import ch.hearc.smb.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Page<Comment> findByPostIdOrderByCreatedDateDesc(Long postId, Pageable pageable);
}
