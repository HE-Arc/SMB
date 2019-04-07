package ch.hearc.sandbox.controller;

import ch.hearc.sandbox.model.Board;
import ch.hearc.sandbox.model.Comment;
import ch.hearc.sandbox.model.CustomUser;
import ch.hearc.sandbox.model.Post;
import ch.hearc.sandbox.service.BoardService;
import ch.hearc.sandbox.service.CommentService;
import ch.hearc.sandbox.service.CustomUserServiceImpl;
import ch.hearc.sandbox.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    BoardService boardService;

    @Autowired
    CommentService commentService;

    @Autowired
    CustomUserServiceImpl customUserDetailsService;

    @GetMapping("/posts/{id}/{pageno}")
    public String specificPost(Map<String, Object> model, @PathVariable Long id, @PathVariable int pageno) {
        Post post = postService.find(id);
        model.put("post", post);
        model.put("postDate", post.getDateDisplay());
        List<Comment> comments = commentService.getAllPostByDesc(post.getId(), pageno);
        model.put("comments", comments);
        List<String> commentDates = comments.stream().map(Comment::getDateDisplay).collect(Collectors.toList());
        model.put("commentDates", commentDates);
        List<String> customUsers = comments.stream().map(c -> c.getUser().getUsername()).collect(Collectors.toList());
        model.put("customUsers", customUsers);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUser actualUser = customUserDetailsService.findByCustomusername(authentication.getName());
        model.put("comment", new Comment(post, actualUser));
        return "post_id";
    }

    @GetMapping("/posts/create")
    public String createPostForm(Map<String, Object> model, @RequestParam Long boardId) {
        Board board = boardService.find(boardId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUser actualUser = customUserDetailsService.findByCustomusername(authentication.getName());
        model.put("post", new Post(board, actualUser));
        return "post_create";
    }

    @GetMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        Post post = postService.find(id);
        Long idBoard = post.getBoard().getId();
        postService.delete(post);
        return "redirect:/boards/" + idBoard;
    }

    @PostMapping("/posts")
    public String createPost(@Valid @ModelAttribute Post post, BindingResult errors, Model model) {
        if (!errors.hasErrors()) {
            postService.save(post);
        }
        return ((errors.hasErrors()) ? "post_create" : "redirect:posts/" + post.getId() + "/0");
    }
}
