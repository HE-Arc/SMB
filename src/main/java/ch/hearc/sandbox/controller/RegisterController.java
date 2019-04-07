package ch.hearc.sandbox.controller;

import ch.hearc.sandbox.dto.PasswordDto;
import ch.hearc.sandbox.model.CustomUser;
import ch.hearc.sandbox.service.CustomUserService;
import ch.hearc.sandbox.service.SecurityServiceImpl;
import ch.hearc.sandbox.validator.CustomUserValidator;
import ch.hearc.sandbox.validator.ResetPasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.UUID;


@Controller
public class RegisterController {

    @Autowired
    CustomUserService customUserService;

    @Autowired
    private SecurityServiceImpl securityService;

    @Autowired
    private CustomUserValidator customUserValidator;

    @Autowired
    private ResetPasswordValidator resetPasswordValidator;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("userForm", new CustomUser());

        return "register";
    }

    @GetMapping("/user/updatePassword")
    public String updatePassword(Model model) {
        model.addAttribute("pwdForm", new PasswordDto());
        return "updatePassword";
    }


    @GetMapping("/forgotpassword")
    public String forgotPassword(Model model, String error, String ok) {
        if (error != null)
            model.addAttribute("error", "Your email is invalid.");
        if (ok != null)
            model.addAttribute("message", "email send");

        return "forgotPassword";
    }

    @RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
    public String forgotPassword(HttpServletRequest request) {
        String email = request.getParameter("email");

        CustomUser user = customUserService.findByCustomemail(email);

        if (user == null) {
            request.setAttribute("error", "1");
            return "forgotpassword";
        }

        String token = UUID.randomUUID().toString();
        customUserService.createPasswordResetTokenForUser(user, token);
        mailSender.send(constructResetTokenEmail(request.getPathInfo(), request.getLocale(), token, user));

        request.setAttribute("ok", "1");
        return "forgotpassword";
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


    @RequestMapping(value = "/user/changePassword", method = RequestMethod.GET)
    public String showChangePasswordPage(Model model,
                                         @RequestParam("id") long id, @RequestParam("token") String token) {
        String result = securityService.validatePasswordResetToken(id, token);
        if (result != null) {
            model.addAttribute("message", "link not valid");
            return "redirect:/login";
        }
        return "redirect:/user/updatePassword";
    }


    @RequestMapping(value = "/user/updatePassword", method = RequestMethod.POST)
    public String registration(HttpServletRequest request,@ModelAttribute("pwdForm") PasswordDto passwordDto, BindingResult bindingResult) {
        resetPasswordValidator.validate(passwordDto, bindingResult);

        if (bindingResult.hasErrors()) {
            return "updatePassword";
        }

        CustomUser user =
                (CustomUser) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();

        customUserService.changeUserPassword(user, passwordDto.getNewPassword());

        return "redirect:/";

    }

    private SimpleMailMessage constructResetTokenEmail(
            String contextPath, Locale locale, String token, CustomUser user) {
        String url = contextPath + "/user/changePassword?id=" +
                user.getId() + "&token=" + token;
        String message = messageSource.getMessage("message.resetPassword",
                null, locale);
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body,
                                             CustomUser user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom("SMB");
        return email;
    }

}
