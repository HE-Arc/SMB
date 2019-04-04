package ch.hearc.sandbox.controller;

import ch.hearc.sandbox.model.CustomUser;
import ch.hearc.sandbox.service.CustomUserService;
import ch.hearc.sandbox.service.SecurityServiceImpl;
import ch.hearc.sandbox.validator.CustomUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;


@Controller
public class RegisterController {

	@Autowired
	CustomUserService customUserService;

	@Autowired
	private SecurityServiceImpl securityService;

	@Autowired
	private CustomUserValidator customUserValidator;

	@GetMapping("/register")
	public String registration(Model model) {
		model.addAttribute("userForm", new CustomUser());

		return "register";
	}


	@PostMapping("/register")
	public String registration(@ModelAttribute("userForm") CustomUser userForm, BindingResult bindingResult) {
		customUserValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "register";
		}

		customUserService.save(userForm);

		securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

		return "redirect:/";
	}

}
