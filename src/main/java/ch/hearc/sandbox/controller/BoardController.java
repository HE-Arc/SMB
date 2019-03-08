package ch.hearc.sandbox.controller;

import ch.hearc.sandbox.data.BoardDAO;
import ch.hearc.sandbox.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class BoardController {
    @Autowired
    BoardDAO boardDAO;

    @GetMapping("/produits")
    public String accueil(Map<String, Object> model, Long id) {
        Board board = boardDAO.find(id);
        model.put("board", board);
        model.put("posts", boardDAO.getPosts(board));
        return "board";
    }
}
