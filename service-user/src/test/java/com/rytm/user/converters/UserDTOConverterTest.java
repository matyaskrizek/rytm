package com.rytm.user.converters;

import com.rytm.user.DTO.UserDTO;
import com.rytm.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserDTOConverterTest {

    private final UserDTOConverter userDTOConverter = new UserDTOConverter();

    @Test
    public void convertToDomain() {
        UserDTO userDTO = UserDTO.builder()
                .password("12345")
                .username("username")
                .email("email")
                .build();

        User user = userDTOConverter.convertToDomain(userDTO);

        Assertions.assertEquals("username", user.getUsername());
        Assertions.assertEquals("email", user.getEmail());
        Assertions.assertEquals("12345", user.getPassword());
    }

    @Test
    public void convertToDTO() {
        User user = new User();
        user.setUsername("username");
        user.setEmail("email");

        UserDTO userDTO = userDTOConverter.convertToDTO(user);

        Assertions.assertEquals("username", userDTO.getUsername());
        Assertions.assertEquals("email", userDTO.getEmail());
    }
}
