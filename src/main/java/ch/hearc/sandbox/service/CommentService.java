package ch.hearc.sandbox.service;

import ch.hearc.sandbox.model.Comment;
import ch.hearc.sandbox.model.Post;
import ch.hearc.sandbox.repository.CommentRepository;
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
public class CommentService {
    @Autowired
    private CommentRepository crepo;

    private final static int SIZEPAGE = 5;

    public List<Comment> findAll() {
        List<Comment> comments = new ArrayList<>();
        crepo.findAll().forEach(comments::add);
        return comments;
    }

    public Page<Comment> getAllPostByDesc(Long postId, Pageable pageable) {
        return crepo.findByPostIdOrderByCreatedDateDesc(postId, pageable);
    }

    public Comment find(Long id) {
        return crepo.findById(id).orElse(new Comment());
    }

    public void save(@Valid Comment comment) {
        crepo.save(comment);
    }

    public void delete(@Valid Comment comment) {
        crepo.delete(comment);
    }

    public void delete(Long id) {
        crepo.delete(this.find(id));
    }

}
