package ch.hearc.sandbox.service;

import ch.hearc.sandbox.model.Post;
import ch.hearc.sandbox.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Component
public class PostService {
    @Autowired
    private PostRepository prepo;

    private final static int SIZEPAGE = 5;

    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        prepo.findAll().forEach(posts::add);
        return posts;
    }

    public List<Post> getAllPostByDesc(Long boardId, int pageno) {
        List<Post> posts = new ArrayList<>();
        prepo.getAllPostsByDesc(PageRequest.of(pageno, SIZEPAGE)).stream().filter(p -> p.getBoard().getId().equals(boardId)).forEach(posts::add);
        return posts;
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
