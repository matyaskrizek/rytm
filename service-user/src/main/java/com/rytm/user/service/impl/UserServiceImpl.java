package com.rytm.user.service.impl;

import com.rytm.user.DTO.UserDTO;
import com.rytm.user.converters.UserDTOConverter;
import com.rytm.user.doa.UserRepository;
import com.rytm.user.domain.User;
import com.rytm.user.service.UserService;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Builder
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDTOConverter userDTOConverter;

    @Override
    public User createUser(UserDTO userDTO) {
        User user = userDTOConverter.convertToDomain(userDTO);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(String username) {
        return userRepository.findUserByUsername(username);
    }
}
