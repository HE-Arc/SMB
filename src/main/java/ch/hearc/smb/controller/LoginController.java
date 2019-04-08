package ch.hearc.smb.controller;


import ch.hearc.smb.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    private CustomUserService customUserService;


    @GetMapping("/login")
    public String login(Model model, String error, String logout, String invalidlink) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        if (invalidlink != null)
            model.addAttribute("error", "Invalid link");


            return "login";
    }


}
