package com.jc.springsecurity.jpa;

import com.jc.springsecurity.pojo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jincheng.zhang
 */

public interface UserRepository extends JpaRepository<User, Long> {
}
