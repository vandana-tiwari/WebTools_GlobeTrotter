package com.webToolsProj.TripOrg.Controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.webToolsProj.TripOrg.POJO.Person;

import org.springframework.validation.ValidationUtils;

public class PersonValidator implements Validator {

    public boolean supports(Class aClass)
    {
        return aClass.equals(Person.class);
    }

    public void validate(Object obj, Errors errors)
    {
        Person person = (Person) obj;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.user", "User Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
        
    }
}
