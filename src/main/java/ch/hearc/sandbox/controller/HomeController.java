package ch.hearc.sandbox.controller;

import ch.hearc.sandbox.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    BoardService boardService;

    @GetMapping("/")
    public String accueil(Map<String, Object> model) {
        return "accueil";
    }

}