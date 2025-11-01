package com.rytm.user.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    public void userTest() {
        User user = new User();
        user.setUsername("username");
        user.setEmail("email");
        user.setPassword("password");
        user.setProfilePhoto(new byte[] {0x01, 0x02, 0x03});

        Assertions.assertEquals("username", user.getUsername());
        Assertions.assertEquals("email", user.getEmail());
        Assertions.assertEquals("password", user.getPassword());
        Assertions.assertEquals(0x01, user.getProfilePhoto()[0]);
    }
}
