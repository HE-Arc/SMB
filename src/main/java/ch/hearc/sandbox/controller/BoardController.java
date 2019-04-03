package ch.hearc.sandbox.controller;

import ch.hearc.sandbox.data.impl.BoardImpl;
import ch.hearc.sandbox.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class BoardController {
    @Autowired
    BoardImpl boardImpl;

    @GetMapping("/boards/{id}")
    public String accueil(Map<String, Object> model, @PathVariable Long id) {
        Board board = boardImpl.find(id);
        model.put("board", board);
        model.put("posts", boardImpl.getPosts(board));
        return "board_id";
    }

    @GetMapping("/boards/create")
    public String createBoardForm(Map<String, Object> model) {
        model.put("board", new Board());
        return "board_create";
    }

    @GetMapping("/boards")
    public String accueil(Map<String, Object> model) {
        model.put("boards", boardImpl.findAll());
        return "accueil";
    }

    @PostMapping("/boards")
    public String createBoard(@Valid @ModelAttribute Board board, BindingResult errors, Model model) {
        if(!errors.hasErrors()) {
            boardImpl.save(board);
        }
        return ((errors.hasErrors()) ? "board_create" : "redirect:boards/" + board.getId());
    }

    @GetMapping("/boards/{id}/delete")
    public String deleteBoard(@PathVariable Long id) {
        boardImpl.delete(id);
        return "redirect:/boards";
    }
}
