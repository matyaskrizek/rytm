package com.rytm.user.service;

import com.rytm.user.DTO.UserDTO;
import com.rytm.user.domain.User;

import java.util.Optional;

public interface UserService {

    User createUser(UserDTO userDTO);

    Optional<User> getUser(String username);
}
