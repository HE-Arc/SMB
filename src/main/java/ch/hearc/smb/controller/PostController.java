package ch.hearc.smb.controller;

import ch.hearc.smb.model.Board;
import ch.hearc.smb.model.Comment;
import ch.hearc.smb.model.CustomUser;
import ch.hearc.smb.model.Post;
import ch.hearc.smb.service.BoardService;
import ch.hearc.smb.service.CommentService;
import ch.hearc.smb.service.CustomUserServiceImpl;
import ch.hearc.smb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/postst2")
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    BoardService boardService;

    @Autowired
    CommentService commentService;

    @Autowired
    CustomUserServiceImpl customUserDetailsService;


    @GetMapping("/{id}")
    public String specificPost(Map<String, Object> model, @PathVariable Long id, @PageableDefault(value=5, page=0) Pageable pageable) {

        Post post = postService.find(id);
        model.put("post", post);
        model.put("postDate", post.getDateDisplay());
        Page<Comment> comments = commentService.getAllPostByDesc(post.getId(), pageable);
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

    @GetMapping("/create")
    public String createPostForm(Map<String, Object> model, @RequestParam Long boardId) {
        Board board = boardService.find(boardId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUser actualUser = customUserDetailsService.findByCustomusername(authentication.getName());
        model.put("post", new Post(board, actualUser));
        return "post_form";
    }

    @GetMapping("/{id}/edit")
    public String editPost(Model model, @PathVariable Long id) {
        model.addAttribute("post", postService.find(id));
        return "post_form";
    }

    @GetMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        Post post = postService.find(id);
        Long idBoard = post.getBoard().getId();
        postService.delete(post);
        return "redirect:/boards/" + idBoard;
    }

    @PostMapping("")
    public String createPost(@Valid @ModelAttribute Post post, BindingResult errors, Model model) {
        if (!errors.hasErrors()) {
            postService.save(post);
        }

        return ((errors.hasErrors()) ? "post_create" : "redirect:posts/" + post.getId());

    }
}
