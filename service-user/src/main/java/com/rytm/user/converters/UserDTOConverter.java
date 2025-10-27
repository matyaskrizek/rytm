package com.rytm.user.converters;

import com.rytm.user.DTO.UserDTO;
import com.rytm.user.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserDTOConverter {
    public UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .profilePhoto(user.getProfilePhoto())
                .build();
    }

    public User convertToDomain(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setProfilePhoto(userDTO.getProfilePhoto());
        user.setPassword(userDTO.getPassword());
        return user;
    }

}
