package com.webToolsProj.TripOrg.Controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.webToolsProj.TripOrg.POJO.Host;

import org.springframework.validation.ValidationUtils;

public class HostValidator implements Validator {

    public boolean supports(Class aClass)
    {
        return aClass.equals(Host.class);
    }

    public void validate(Object obj, Errors errors)
    {
        Host host = (Host) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fName", "error.invalid.user", "First Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lName", "error.invalid.user", "Last Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "govtID", "error.invalid.user", "User Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idType", "error.invalid.password", "Password Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.user", "User Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email.emailId", "error.invalid.email.emailId", "Email Required");
    }
}
