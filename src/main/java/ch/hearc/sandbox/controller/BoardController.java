package ch.hearc.sandbox.controller;

import ch.hearc.sandbox.service.BoardService;
import ch.hearc.sandbox.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class BoardController {
    @Autowired
    BoardService boardService;

    @GetMapping("/boards/{id}")
    public String specificBoard(Map<String, Object> model, @PathVariable Long id) {
        Board board = boardService.find(id);
        model.put("board", board);
        model.put("posts", board.getPosts());
        return "board_id";
    }

    @GetMapping("/boards/create")
    public String createBoardForm(Map<String, Object> model) {
        model.put("board", new Board());
        return "board_create";
    }

    @GetMapping("/boards")
    public String allBoards(Map<String, Object> model) {
        model.put("boards", boardService.findAll());
        return "boards";
    }

    @GetMapping("/boards/{id}/delete")
    public String deleteBoard(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/boards";
    }

    @PostMapping("/boards")
    public String createBoard(@Valid @ModelAttribute Board board, BindingResult errors, Model model) {
        if (!errors.hasErrors()) {
            boardService.save(board);
        }
        return ((errors.hasErrors()) ? "board_create" : "redirect:boards/" + board.getId());
    }
}
