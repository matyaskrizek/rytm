package com.rytm.user.dao;

import com.rytm.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import java.util.Optional;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveAndGet_Success() {
        User user = new User();
        user.setEmail("test@email.com");
        user.setUsername("test");

        userRepository.save(user);

        User user2 = new User();
        user2.setEmail("test2@email.com");
        user2.setUsername("test2");

        userRepository.save(user2);

        Optional<User> getUser = userRepository.findUserByUsername(user.getUsername());
        Assertions.assertTrue(getUser.isPresent());
        Assertions.assertEquals(user, getUser.get());

        Optional<User> getUser2 = userRepository.findUserByEmail(user2.getEmail());
        Assertions.assertTrue(getUser2.isPresent());
        Assertions.assertEquals(user2, getUser2.get());
    }
}
