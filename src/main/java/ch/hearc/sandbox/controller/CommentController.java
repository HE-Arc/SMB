package ch.hearc.sandbox.controller;

import ch.hearc.sandbox.data.impl.CommentImpl;
import ch.hearc.sandbox.data.impl.PostImpl;
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
    CommentImpl commentImpl;

    @Autowired
    PostImpl postImpl;

    @PostMapping("/comments")
    public String createBoard(@Valid @ModelAttribute Comment comment, BindingResult errors, Model model) {
        if (!errors.hasErrors()) {
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
