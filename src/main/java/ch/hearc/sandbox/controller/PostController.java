package ch.hearc.sandbox.controller;

import ch.hearc.sandbox.data.impl.BoardImpl;
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
public class PostController {
//    @Autowired
//    PostController postImpl;
//
//    @GetMapping("/posts/{id}")
//    public String accueil(Map<String, Object> model, @PathVariable Long id) {
//        Board board = postImpl.find(id);
//        model.put("board", board);
//        model.put("posts", postImpl.getPosts(board));
//        return "board_id";
//    }
//
//    @GetMapping("/posts/create")
//    public String createBoardForm(Map<String, Object> model) {
//        model.put("board", new Board());
//        return "board_create";
//    }
//
//    @GetMapping("/posts")
//    public String accueil(Map<String, Object> model) {
//        model.put("posts", postImpl.findAll());
//        return "posts";
//    }
//
//    @PostMapping("/posts")
//    public String createBoard(@Valid @ModelAttribute Board board, BindingResult errors, Model model) {
//        if(!errors.hasErrors()) {
//            postImpl.save(board);
//        }
//        return ((errors.hasErrors()) ? "board_create" : "redirect:posts/" + board.getId());
//    }
//
//    @GetMapping("/posts/{id}/delete")
//    public String deleteBoard(@PathVariable Long id) {
//        postImpl.delete(id);
//        return "redirect:/posts";
//    }
}
