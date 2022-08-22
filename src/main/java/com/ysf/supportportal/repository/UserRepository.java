package com.ysf.supportportal.repository;

import com.ysf.supportportal.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {
    User findByUsername(String username);

    User findByEmail(String email);

}
