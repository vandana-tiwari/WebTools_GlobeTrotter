package com.webToolsProj.TripOrg.Controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.webToolsProj.TripOrg.POJO.AdvertisementApartment;



@Component
public class AdvertAptValidator implements Validator {

    public boolean supports(Class aClass)
    {
        return aClass.equals(AdvertisementApartment.class);
    }

    public void validate(Object obj, Errors errors)
    {
    	AdvertisementApartment advertisementApartment = (AdvertisementApartment) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.invalid.category", "Title Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "checkIn", "error.invalid.category", "Check In Date Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "checkOut", "error.invalid.category", "Check Out Date Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rooms", "error.invalid.category", "Room Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numOfBeds", "error.invalid.category", "Number of beds Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bathrooms", "error.invalid.category", "Bathroom Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "furnished", "error.invalid.category", "Furnished Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "error.invalid.category", "Price Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "maxOccupants", "error.invalid.category", "Occupants Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "priceExtraOccupant", "error.invalid.category", "Price Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "error.invalid.category", "Message Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.street", "error.invalid.category", "Street Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.city", "error.invalid.category", "City Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.state", "error.invalid.category", "State Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.zip", "error.invalid.category", "Zip Required");
        
    }
}
