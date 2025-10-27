package com.rytm.user.api;

import com.rytm.user.DTO.UserDTO;
import com.rytm.user.domain.User;
import com.rytm.user.service.UserService;
import com.rytm.user.api.validators.UserDTOValidator;
import com.rytm.user.converters.UserDTOConverter;
import common.util.errors.ErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    final private UserService userService;
    final private UserDTOConverter userDTOConverter;
    final private UserDTOValidator userDTOValidator;

    @Operation(summary ="Create a user")
    @RequestMapping(path = "user/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        userDTOValidator.validate(userDTO);
        if(userService.getUser(userDTO.getUsername()).isPresent()) {
            ErrorDTO errorDTO = new ErrorDTO("username", "username " + userDTO.getUsername() + " already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDTO);
        }
        if(userService.getUserByEmail(userDTO.getEmail()).isPresent()) {
            ErrorDTO errorDTO = new ErrorDTO("email", "email " + userDTO.getEmail() + " is already in use");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDTO);
        }
        UserDTO returnUserDTO = userDTOConverter.convertToDTO(userService.createUser(userDTO));
        return  ResponseEntity.status(HttpStatus.CREATED).body(returnUserDTO);
    }

    @Operation(summary = "Get a user by username")
    @RequestMapping(path = "user/{username}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUser(@PathVariable("username") String username) {
        Optional<User> optionalUser = userService.getUser(username);
        if (optionalUser.isPresent()) {
            UserDTO userDTO = userDTOConverter.convertToDTO(optionalUser.get());
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get a user by email")
    @RequestMapping(path = "user/email/{email}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable("email") String email) {
        Optional<User> optionalUser = userService.getUserByEmail(email);
        if (optionalUser.isPresent()) {
            UserDTO userDTO = userDTOConverter.convertToDTO(optionalUser.get());
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.notFound().build();
    }

}