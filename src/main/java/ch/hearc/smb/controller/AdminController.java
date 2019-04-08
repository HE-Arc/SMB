package ch.hearc.smb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String admin(Map<String, Object> model) {
        return "admin";
    }

}

