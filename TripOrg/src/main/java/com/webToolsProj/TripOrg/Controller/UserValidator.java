package com.webToolsProj.TripOrg.Controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.webToolsProj.TripOrg.POJO.User;

import org.springframework.validation.ValidationUtils;

public class UserValidator implements Validator {

    public boolean supports(Class aClass)
    {
        return aClass.equals(User.class);
    }

    public void validate(Object obj, Errors errors)
    {
        User user = (User) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fName", "error.invalid.user", "First Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lName", "error.invalid.user", "Last Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.user", "User Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email.emailId", "error.invalid.email.emailId", "Email Required");
    }
}
