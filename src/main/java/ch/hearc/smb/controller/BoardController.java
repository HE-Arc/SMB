package ch.hearc.smb.controller;


import ch.hearc.smb.model.Post;

import ch.hearc.smb.service.BoardService;
import ch.hearc.smb.model.Board;
import ch.hearc.smb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    @GetMapping("/create")
    public String createBoardForm(Map<String, Object> model) {
        model.put("board", new Board());
        return "board_create";
    }

    @GetMapping("")
    public String allBoards(Map<String, Object> model) {
        model.put("boards", boardService.findAll());
        return "boards";
    }

    @GetMapping("/{id}/delete")
    public String deleteBoard(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/boards";
    }

    @PostMapping("")
    public String createBoard(@Valid @ModelAttribute Board board, BindingResult errors, Model model) {
        if (!errors.hasErrors()) {
            boardService.save(board);
        }

        return ((errors.hasErrors()) ? "board_create" : "redirect:boards/" + board.getId() + "/0");

    }
}
