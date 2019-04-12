package ch.hearc.smb.validator;

import ch.hearc.smb.model.CustomUser;
import ch.hearc.smb.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CustomUserValidator implements Validator {

    @Autowired
    private CustomUserService customUserService;

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomUser.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CustomUser user = (CustomUser) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (customUserService.findByCustomusername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (validateEmail(user.getEmail())) {
            errors.rejectValue("email", "Size.userForm.email");
        }
        if (customUserService.findByCustomemail(user.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("pwd", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("pwdConfirm", "Diff.userForm.passwordConfirm");
        }
    }


    private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    private static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return !matcher.matches();
    }
}
