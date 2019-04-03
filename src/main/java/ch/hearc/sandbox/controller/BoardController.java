package ch.hearc.sandbox.controller;

import ch.hearc.sandbox.data.impl.BoardImpl;
import ch.hearc.sandbox.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
public class BoardController {
    @Autowired
    BoardImpl boardIml;

    @GetMapping("/boards/{id}")
    public String accueil(Map<String, Object> model, @PathVariable Long id) {
        Board board = boardIml.find(id);
        model.put("board", board);
        model.put("posts", boardIml.getPosts(board));
        return "board";
    }
}
