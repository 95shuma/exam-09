package com.exam9.forum.util;

import com.exam9.forum.model.User;
import com.exam9.forum.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PreloadDB {
    private final PasswordEncoder encoder;

    public PreloadDB(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Bean
    CommandLineRunner initDatabase(UserRepository ur){
        return (args) -> {
            ur.deleteAll();
            User user = new User();
            user.setMail("admin@a.r");
            user.setName("admin");
            user.setLogin("admin");
            user.setPassword(encoder.encode("admin"));
            ur.save(user);
        };
    }
}
