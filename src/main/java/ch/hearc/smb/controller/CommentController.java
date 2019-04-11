package ch.hearc.smb.controller;


import ch.hearc.smb.model.Comment;
import ch.hearc.smb.model.Post;
import ch.hearc.smb.service.CommentService;
import ch.hearc.smb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Convert;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    PostService postService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODO') or #ownerName == authentication.name")
    @PostMapping("/delete")
    public String deleteComment(@RequestParam Long commentId, @RequestParam String ownerName) {
        System.out.println("COUCOU");
        Comment comment = commentService.find(commentId);
        Long idPost = comment.getPost().getId();
        commentService.delete(comment);
        return "redirect:/posts/" + idPost;
    }

    @GetMapping(value = "/{boardid}", produces = "application/json")
    public @ResponseBody
    Page<Comment> getComments(@PathVariable Long boardid, @PageableDefault(value = 5, page = 0) Pageable pageable) {
        return commentService.getAllPostByDesc(boardid, pageable);
    }

    @PostMapping("")
    public String createComments(@ModelAttribute @Validated Comment comment, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            return "redirect:posts/" + comment.getPost().getId() + "?error=1";
        }
        commentService.save(comment);
        Post post = comment.getPost();
        post.setModifiedDate(comment.getCreatedDate());
        postService.save(post);
        return "redirect:posts/" + comment.getPost().getId();
    }
}
