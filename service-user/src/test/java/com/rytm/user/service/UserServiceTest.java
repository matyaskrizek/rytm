package com.rytm.user.service;

import com.rytm.user.DTO.UserDTO;
import com.rytm.user.converters.UserDTOConverter;
import com.rytm.user.dao.UserRepository;
import com.rytm.user.domain.User;
import com.rytm.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        userRepository = Mockito.mock(UserRepository.class);
        final UserDTOConverter userDTOConverter = new UserDTOConverter();
        userService = new UserServiceImpl(userRepository, userDTOConverter);
    }

    @Test
    public void createUser_Success() {
        UserDTO userDTO = UserDTO.builder()
                .username("test")
                .email("test@email.com")
                .password("1234ABC")
                .build();

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());

        Mockito.when(userRepository.save(any())).thenReturn(user);

        User retUser = userService.createUser(userDTO);

        Assertions.assertEquals(retUser, user);
    }

    @Test
    public void getUser_Success() {
        User user = new User();
        user.setEmail("test@email.com");
        user.setUsername("test");

        Mockito.when(userRepository.findUserByUsername(user.getUsername())).thenReturn(Optional.of(user));

        Optional<User> retUser = userService.getUser(user.getUsername());

        Assertions.assertTrue(retUser.isPresent());
        User trueUser = retUser.get();
        Assertions.assertEquals(trueUser, user);
    }

    @Test
    public void getUser_NotFound() {
        User user = new User();
        user.setEmail("test@email.com");
        user.setUsername("test");

        Optional<User> retUser = userService.getUser(user.getUsername());

        Assertions.assertFalse(retUser.isPresent());
    }

    @Test
    public void getUserByEmail_Success() {
        User user = new User();
        user.setEmail("test@email.com");
        user.setUsername("test");

        Mockito.when(userRepository.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));

        Optional<User> retUser = userService.getUserByEmail(user.getEmail());

        Assertions.assertTrue(retUser.isPresent());
        User trueUser = retUser.get();
        Assertions.assertEquals(trueUser, user);
    }

    @Test
    public void getUserByEmail_NotFound() {
        User user = new User();
        user.setEmail("test@email.com");
        user.setUsername("test");

        Optional<User> retUser = userService.getUserByEmail(user.getEmail());

        Assertions.assertFalse(retUser.isPresent());
    }
}
