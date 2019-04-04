package ch.hearc.sandbox.controller;

import ch.hearc.sandbox.data.impl.BoardImpl;
import ch.hearc.sandbox.data.impl.CommentImpl;
import ch.hearc.sandbox.data.impl.PostImpl;
import ch.hearc.sandbox.model.Board;
import ch.hearc.sandbox.model.Comment;
import ch.hearc.sandbox.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    CommentImpl commentImpl;

    @Autowired
    PostImpl postImpl;

//    @GetMapping("/comments/{id}")
//    public String accueil(Map<String, Object> model, @PathVariable Long id) {
//        Comment comment = commentImpl.find(id);
//        model.put("comment", comment);
//        return "comment_id";
//    }

//    @GetMapping("/comments/create")
//    public String createPostForm(Map<String, Object> model, @RequestParam Long postId) {
//        Post post = postImpl.find(postId);
//        model.put("comment", new Comment(post));
//        return "comment_create";
//    }
//
//    @GetMapping("/comments")
//    public String accueil(Map<String, Object> model) {
//        model.put("comments", commentImpl.findAll());
//        return "comments";
//    }
//
    @PostMapping("/comments")
    public String createBoard(@Valid @ModelAttribute Comment comment, BindingResult errors, Model model) {
        if(!errors.hasErrors()) {
            commentImpl.save(comment);
        }
        return "redirect:posts/" + comment.getPost().getId();
    }

    @GetMapping("/comments/{id}/delete")
    public String deleteBoard(@PathVariable Long id) {
        Comment comment = commentImpl.find(id);
        Long idPost = comment.getPost().getId();
        commentImpl.delete(comment);
        return "redirect:/posts/" + idPost;
    }
}
