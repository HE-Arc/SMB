package ch.hearc.sandbox.repository;

import ch.hearc.sandbox.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    //Source : https://stackoverflow.com/questions/32434058/how-to-implement-pagination-in-spring-boot-with-hibernate
    @Query(getAllPostsDesc)
    List<Post> getAllPostsByDesc(Pageable pageable);

    String getAllPostsDesc = "from Post order by modifiedDate DESC";
}
