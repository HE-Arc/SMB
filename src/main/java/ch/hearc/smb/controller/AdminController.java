package ch.hearc.smb.controller;


import ch.hearc.smb.model.CustomUser;
import ch.hearc.smb.model.Role;
import ch.hearc.smb.repository.RoleRepository;
import ch.hearc.smb.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
public class AdminController {

    @Autowired
    CustomUserService customUserService;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/admin")
    public String admin(Map<String, Object> model) {
        return "admin";
    }

    @PostMapping("/admin")
    public String admin(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
        Role roleModo = roleRepository.findByName("ROLE_MODO");
        List<CustomUser> users = null;
        if (username != "") {
            users = customUserService.findByUsernameContaining(username);
        }
        model.addAttribute("usernames", users);
        model.addAttribute("roleAdmin", roleAdmin);
        model.addAttribute("roleModo", roleModo);

        return "admin";
    }

}

