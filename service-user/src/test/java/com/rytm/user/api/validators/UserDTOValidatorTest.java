package com.rytm.user.api.validators;

import com.rytm.user.DTO.UserDTO;
import common.util.errors.ErrorDTO;
import common.util.exceptions.DTOValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserDTOValidatorTest {
    private final UserDTOValidator userDTOValidator = new UserDTOValidator();

    @Test
    public void validate_Success(){
        UserDTO userDTO = UserDTO.builder()
                .password("12345")
                .username("username")
                .email("email")
                .build();

        userDTOValidator.validate(userDTO);
    }


    @Test
    public void validate_Error(){
        UserDTO userDTO = UserDTO.builder()
                .build();

        DTOValidationException exception = Assertions.assertThrows(DTOValidationException.class, () ->userDTOValidator.validate(userDTO));
        List<ErrorDTO> errorsDTOList = exception.getErrors();
        Assertions.assertEquals(3, errorsDTOList.size());
        ErrorDTO error1 = errorsDTOList.get(0);
        ErrorDTO error2 = errorsDTOList.get(1);
        ErrorDTO error3 = errorsDTOList.get(2);
        Assertions.assertEquals("username should not be empty or null", error1.getMessage());
        Assertions.assertEquals("email should not be empty or null", error2.getMessage());
        Assertions.assertEquals("password should not be empty or null", error3.getMessage());
    }
}
