package ch.hearc.sandbox.controller;


import ch.hearc.sandbox.model.Post;

import ch.hearc.sandbox.service.CommentService;
import ch.hearc.sandbox.service.PostService;
import ch.hearc.sandbox.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    PostService postService;

    @GetMapping("/comments/{id}/delete")
    public String deleteComment(@PathVariable Long id) {
        Comment comment = commentService.find(id);
        Long idPost = comment.getPost().getId();
        commentService.delete(comment);
        return "redirect:/posts/" + idPost;
    }

    @PostMapping("/comments")
    public String createComments(@Valid @ModelAttribute Comment comment, BindingResult errors, Model model) {
        if (!errors.hasErrors()) {
            commentService.save(comment);
    Post post = comment.getPost();
            post.setModifiedDate(comment.getCreatedDate());
            postService.save(post);
        }
        return "redirect:posts/" + comment.getPost().getId();

    }
}
