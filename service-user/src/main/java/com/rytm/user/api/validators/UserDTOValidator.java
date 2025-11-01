package com.rytm.user.api.validators;

import com.rytm.user.DTO.UserDTO;
import common.util.DTOValidator;
import common.util.exceptions.DTOValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class UserDTOValidator implements DTOValidator<UserDTO> {
    @Override
    public void validate(UserDTO toValidate) {
        DTOValidationException errors = new DTOValidationException("UserDTOValidation exception");
        if(StringUtils.isBlank(toValidate.getUsername())) {
            errors.addError("username", "username should not be empty or null");
        }
        if(StringUtils.isBlank(toValidate.getEmail())) {
            errors.addError("email", "email should not be empty or null");
        }
        if(StringUtils.isBlank(toValidate.getPassword())) {
            errors.addError("password", "password should not be empty or null");
        }
        errors.throwIfErrors();
    }
}
