package ch.hearc.sandbox.controller;

import ch.hearc.sandbox.data.impl.BoardImpl;
import ch.hearc.sandbox.data.impl.CommentImpl;
import ch.hearc.sandbox.data.impl.PostImpl;
import ch.hearc.sandbox.model.Board;
import ch.hearc.sandbox.model.Comment;
import ch.hearc.sandbox.model.CustomUser;
import ch.hearc.sandbox.model.Post;
import ch.hearc.sandbox.service.CustomUserDetailsServiceImpl;
import ch.hearc.sandbox.service.CustomUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class PostController {
    @Autowired
    PostImpl postImpl;

    @Autowired
    BoardImpl boardImpl;

    @Autowired
    CommentImpl commentImpl;

    @Autowired
    CustomUserServiceImpl customUserDetailsService;

    @GetMapping("/posts/{id}")
    public String accueil(Map<String, Object> model, @PathVariable Long id) {
        Post post = postImpl.find(id);
        model.put("post", post);
        List<String> customUsers = post.getComments().stream().map(c -> c.getUser().getUsername()).collect(Collectors.toList());
        model.put("customUsers", customUsers);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUser actualUser = customUserDetailsService.findByCustomusername(authentication.getName());
        model.put("comment", new Comment(post, actualUser));
        return "post_id";
    }

    @GetMapping("/posts/create")
    public String createPostForm(Map<String, Object> model, @RequestParam Long boardId) {
        Board board = boardImpl.find(boardId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUser actualUser = customUserDetailsService.findByCustomusername(authentication.getName());
        model.put("post", new Post(board, actualUser));
        return "post_create";
    }
//
//    @GetMapping("/posts")
//    public String accueil(Map<String, Object> model) {
//        model.put("posts", postImpl.findAll());
//        return "posts";
//    }
//
    @PostMapping("/posts")
    public String createBoard(@Valid @ModelAttribute Post post, BindingResult errors, Model model) {
        if(!errors.hasErrors()) {
            postImpl.save(post);
        }
        return ((errors.hasErrors()) ? "post_create" : "redirect:posts/" + post.getId());
    }

    @GetMapping("/posts/{id}/delete")
    public String deleteBoard(@PathVariable Long id) {
        Post post = postImpl.find(id);
        Long idBoard = post.getBoard().getId();
        postImpl.delete(post);
        return "redirect:/boards/" + idBoard;
    }
}