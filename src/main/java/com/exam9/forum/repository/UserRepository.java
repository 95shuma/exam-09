package com.exam9.forum.repository;

import com.exam9.forum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByMail(String mail);
    boolean existsUserByMail(String mail);

}
