package ch.hearc.smb.controller;

import ch.hearc.smb.model.Board;
import ch.hearc.smb.model.Comment;
import ch.hearc.smb.model.CustomUser;
import ch.hearc.smb.model.Post;
import ch.hearc.smb.service.BoardService;
import ch.hearc.smb.service.CommentService;
import ch.hearc.smb.service.CustomUserService;
import ch.hearc.smb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    BoardService boardService;

    @Autowired
    CommentService commentService;

    @Autowired
    CustomUserService customUserDetailsService;


    @GetMapping("/{id}")
    public String specificPost(Model model, @PathVariable Long id, @PageableDefault(value = 5, page = 0) Pageable pageable, @RequestParam(required = false) String error) {
        if (error != null) {
            model.addAttribute("error", "size must be between 1 and 300");
        } else {
            model.addAttribute("error", "");
        }

        Post post = postService.find(id);
        model.addAttribute("post", post);
        model.addAttribute("postDate", post.getDateDisplay());
        Page<Comment> comments = commentService.getAllPostByDesc(post.getId(), pageable);
        model.addAttribute("comments", comments);
        List<String> commentDates = comments.stream().map(Comment::getDateDisplay).collect(Collectors.toList());
        model.addAttribute("commentDates", commentDates);
        List<String> customUsers = comments.stream().map(c -> c.getUser().getUsername()).collect(Collectors.toList());
        model.addAttribute("customUsers", customUsers);
        List<Long> idUser = comments.stream().map(c -> c.getUser().getId()).collect(Collectors.toList());
        model.addAttribute("idUser", idUser);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUser actualUser = customUserDetailsService.findByCustomusername(authentication.getName());
        model.addAttribute("currentUser", actualUser);
        model.addAttribute("comment", new Comment(post, actualUser));
        return "post_id";
    }

    @GetMapping("/create")
    public String createPostForm(Model model, @RequestParam Long boardId) {
        Board board = boardService.find(boardId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUser actualUser = customUserDetailsService.findByCustomusername(authentication.getName());
        model.addAttribute("post", new Post(board, actualUser));
        return "post_form";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODO') or #post.user.username == authentication.name")
    @PostMapping("/delete")
    public String deletePost(@RequestParam Post post) {
        Long idBoard = post.getBoard().getId();
        postService.delete(post);
        return "redirect:/boards/" + idBoard;
    }

    @PostMapping("")
    public String createPost(@ModelAttribute @Validated Post post, BindingResult errors) {
        if (errors.hasErrors()) {
            return "post_form";
        }
        postService.save(post);
        return "redirect:posts/" + post.getId();
    }
}
