package com.codemart.karmawebshop.controller.validator;

import com.codemart.karmawebshop.entity.User;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator {


    public String validate(User user) {
        if (!isAlphaNum(user.getUsername()))
            return "username";

        if (!isAlphaNum(user.getPassword()) && user.getPassword().length()>8){
            return "password";
        }

        if (!isEmail(user.getEmail())){
            return "email";
        }
        if (!isValidRole(user.getRole())){
            return "role";
        }

        return "ok";
    }

    private boolean isEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    private boolean isValidRole(String role) {
        if ((role != null) && (role.equals("USER") || role.equals("ADMIN")))
            return true;
        else return false;
    }

    private boolean isAlphaNum(String username) {
        if ((username != null) && username.matches("[A-Za-z0-9_]+"))
            return true;
        else return false;
    }
}
