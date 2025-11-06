package com.rytm.user.system;

import com.rytm.user.dao.UserRepository;
import com.rytm.user.system.tests.UserOperationsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.springframework.boot.test.mock.mockito.MockBean;

public class SystemIntegrationTest {

    @MockBean
    private UserRepository userRepository;

    @Nested
    @DisplayName("User Operations Test")
    @Order(1)
    public class UserOperationsTestSuite extends UserOperationsTest {

    }


}
