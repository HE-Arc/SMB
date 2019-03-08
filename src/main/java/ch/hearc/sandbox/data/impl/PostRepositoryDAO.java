package ch.hearc.sandbox.data.impl;

import ch.hearc.sandbox.data.PostDAO;
import ch.hearc.sandbox.model.Post;
import ch.hearc.sandbox.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Component
public class PostRepositoryDAO implements PostDAO {
    @Autowired
    private PostRepository prepo;


    @Override
    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        prepo.findAll().forEach(posts::add);
        return posts;
    }

    @Override
    public Post find(Long id) {
        return prepo.findById(id).orElse(new Post());
    }

    @Override
    public void save(@Valid Post post) {
        prepo.save(post);
    }

}
