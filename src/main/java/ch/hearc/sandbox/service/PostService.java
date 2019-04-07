package ch.hearc.sandbox.service;

import ch.hearc.sandbox.model.Post;
import ch.hearc.sandbox.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Component
public class PostService {
    @Autowired
    private PostRepository prepo;

    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        prepo.findAll().forEach(posts::add);
        return posts;
    }

    public Page<Post> getAllPostByDesc(Long boardId, Pageable pageable) {
        return prepo.findByBoardIdOrderByModifiedDateDesc(boardId,pageable);
    }

    public Post find(Long id) {
        return prepo.findById(id).orElse(new Post());
    }

    public void save(@Valid Post post) {
        prepo.save(post);
    }

    public void delete(@Valid Post post) {
        prepo.delete(post);
    }

    public void delete(Long id) {
        prepo.delete(this.find(id));
    }


}
