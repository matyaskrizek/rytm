package com.rytm.user.doa;

import com.rytm.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository()
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT ua FROM User ua WHERE ua.username = :username")
    Optional<User> findUserByUsername(@Param("username") String username);

}
