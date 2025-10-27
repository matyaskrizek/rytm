package com.rytm.user.service;

import com.rytm.user.DTO.UserDTO;
import com.rytm.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public interface UserService {

    User createUser(UserDTO userDTO);

    Optional<User> getUser(String username);

    Optional<User> getUserByEmail(String email);
}
