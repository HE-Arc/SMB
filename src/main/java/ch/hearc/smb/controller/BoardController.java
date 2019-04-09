package ch.hearc.smb.controller;


import ch.hearc.smb.model.Board;
import ch.hearc.smb.model.Post;
import ch.hearc.smb.service.BoardService;
import ch.hearc.smb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/boards")
public class BoardController {
    @Autowired
    BoardService boardService;

    @Autowired PostService postService;

    @GetMapping("/{id}")
    public String specificBoard(Map<String, Object> model, @PathVariable Long id, @PageableDefault(value=5, page=0) Pageable pageable) {
        Board board = boardService.find(id);
        Page<Post> posts = postService.getAllPostByDesc(board.getId(), pageable);
        List<String> dates = posts.stream().map(Post::getDateDisplay).collect(Collectors.toList());

        model.put("board", board);
        model.put("posts", posts);
        model.put("dates", dates);
        model.put("pageUrl", "/boards/" + board.getId());
        return "board_id";
    }

    @GetMapping("/{id}/search/{searchContent}")
    public String searchPostInBoard(Map<String, Object> model, @PathVariable Long id, @PathVariable String searchContent, @PageableDefault(value=5, page=0) Pageable pageable) {
        Board board = boardService.find(id);
        Page<Post> posts = postService.findByName(board.getId(), searchContent, pageable);
        List<String> dates = posts.stream().map(Post::getDateDisplay).collect(Collectors.toList());

        model.put("board", board);
        model.put("posts", posts);
        model.put("dates", dates);
        model.put("searchContent", searchContent);
        model.put("pageUrl", "/boards/" + board.getId() + "/search/" + searchContent);
        return "board_id";
    }


    @Secured({ "ROLE_ADMIN", "ROLE_MODO" })
    @GetMapping("/create")
    public String createBoardForm(Map<String, Object> model) {
        model.put("board", new Board());
        return "board_form";
    }

    @Secured({ "ROLE_ADMIN", "ROLE_MODO" })
    @GetMapping("/{id}/edit")
    public String modifyBoardForm(Model model, @PathVariable Long id) {
        System.out.println(boardService.find(id).getId());
        model.addAttribute("board", boardService.find(id));
        return "board_form";
    }

    @GetMapping("")
    public String allBoards(Map<String, Object> model) {
        model.put("boards", boardService.findAll());
        return "boards";
    }

    @Secured({ "ROLE_ADMIN", "ROLE_MODO" })
    @GetMapping("/{id}/delete")
    public String deleteBoard(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/boards";
    }

    @Secured({ "ROLE_ADMIN", "ROLE_MODO" })
    @PostMapping("")
    public String createBoard(@ModelAttribute("board") @Validated Board board, BindingResult errors) {
        if (errors.hasErrors()) {
            return "board_form";
        }
        boardService.save(board);
        return "redirect:boards/" + board.getId();
    }
}
