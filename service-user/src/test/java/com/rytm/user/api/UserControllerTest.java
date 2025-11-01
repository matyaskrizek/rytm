package com.rytm.user.api;

import com.rytm.user.DTO.UserDTO;
import com.rytm.user.api.validators.UserDTOValidator;
import com.rytm.user.converters.UserDTOConverter;
import com.rytm.user.domain.User;
import com.rytm.user.service.UserService;
import common.util.errors.ErrorDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class UserControllerTest {
    private UserService userService;

    private UserController userController;

    @BeforeEach
    public void setup() {
        userService = Mockito.mock(UserService.class);
        final UserDTOConverter userDTOConverter = new UserDTOConverter();
        final UserDTOValidator userDTOValidator = new UserDTOValidator();
        userController = new UserController(userService, userDTOConverter, userDTOValidator);
    }

    @Test
    public void createUserSuccess() {
        UserDTO userDTO = UserDTO.builder()
                .username("test")
                .email("test@email.com")
                .password("1234ABC")
                .build();

        User retUser = new User();
        retUser.setEmail(userDTO.getEmail());
        retUser.setPassword(userDTO.getPassword());
        retUser.setUsername(userDTO.getUsername());

        Mockito.when(userService.createUser(userDTO)).thenReturn(retUser);

        ResponseEntity<?> response = userController.createUser(userDTO);
        UserDTO responseUser = (UserDTO) response.getBody();

        UserDTO expectedUserDTO = UserDTO.builder()
                .username("test")
                .email("test@email.com")
                .build();

        Assertions.assertEquals(expectedUserDTO, responseUser);

    }

    @Test
    public void createUser_withExistingUsername_Error() {
        UserDTO userDTO = UserDTO.builder()
                .username("test")
                .email("test@email.com")
                .password("1234ABC")
                .build();

        User retUser = new User();
        retUser.setEmail(userDTO.getEmail());
        retUser.setPassword(userDTO.getPassword());
        retUser.setUsername(userDTO.getUsername());

        Mockito.when(userService.getUser(userDTO.getUsername())).thenReturn(Optional.of(retUser));

        ResponseEntity<?> response = userController.createUser(userDTO);

        Assertions.assertEquals(HttpStatusCode.valueOf(409), response.getStatusCode());
        ErrorDTO responseError = (ErrorDTO) response.getBody();
        Assertions.assertEquals("username " + userDTO.getUsername() + " already exists", responseError.getMessage());

    }


    @Test
    public void createUser_withExistingEmail_Error() {
        UserDTO userDTO = UserDTO.builder()
                .username("test2")
                .email("test@email.com")
                .password("1234ABC")
                .build();

        User retUser = new User();
        retUser.setEmail(userDTO.getEmail());
        retUser.setPassword(userDTO.getPassword());
        retUser.setUsername(userDTO.getUsername());

        Mockito.when(userService.getUserByEmail(userDTO.getEmail())).thenReturn(Optional.of(retUser));

        ResponseEntity<?> response = userController.createUser(userDTO);

        Assertions.assertEquals(HttpStatusCode.valueOf(409), response.getStatusCode());
        ErrorDTO responseError = (ErrorDTO) response.getBody();
        Assertions.assertEquals("email " + userDTO.getEmail() + " is already in use", responseError.getMessage());

    }

    @Test
    public void getUser_Success() {
        User user = new User();
        user.setEmail("test@email.com");
        user.setUsername("test");

        Mockito.when(userService.getUser(user.getUsername())).thenReturn(Optional.of(user));

        ResponseEntity<?> response = userController.getUser(user.getUsername());

        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        UserDTO responseUser = (UserDTO) response.getBody();

        UserDTO expectedUserDTO = UserDTO.builder()
                .username("test")
                .email("test@email.com")
                .build();

        Assertions.assertEquals(expectedUserDTO, responseUser);
    }

    @Test
    public void getUser_NotFound() {
        ResponseEntity<?> response = userController.getUser("test");

        Assertions.assertEquals(HttpStatusCode.valueOf(404), response.getStatusCode());
    }

    @Test
    public void getUserByEmail_Success() {
        User user = new User();
        user.setEmail("test@email.com");
        user.setUsername("test");

        Mockito.when(userService.getUserByEmail(user.getEmail())).thenReturn(Optional.of(user));

        ResponseEntity<?> response = userController.getUserByEmail(user.getEmail());

        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        UserDTO responseUser = (UserDTO) response.getBody();

        UserDTO expectedUserDTO = UserDTO.builder()
                .username("test")
                .email("test@email.com")
                .build();

        Assertions.assertEquals(expectedUserDTO, responseUser);
    }

    @Test
    public void getUserByEmail_NotFound() {
        ResponseEntity<?> response = userController.getUserByEmail("test@email.com");

        Assertions.assertEquals(HttpStatusCode.valueOf(404), response.getStatusCode());
    }


}
