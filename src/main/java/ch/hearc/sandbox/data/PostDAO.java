package ch.hearc.sandbox.data;

import ch.hearc.sandbox.model.Post;

import javax.validation.Valid;
import java.util.List;

public interface PostDAO {
    List<Post> findAll();

    Post find(Long id);

    void save(@Valid Post post);
}
