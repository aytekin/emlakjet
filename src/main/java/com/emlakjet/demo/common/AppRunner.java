package com.emlakjet.demo.common;

import com.emlakjet.demo.common.util.Mapper;
import com.emlakjet.demo.dto.UserDto;
import com.emlakjet.demo.entity.User;
import com.emlakjet.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class AppRunner implements ApplicationRunner {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;
    private static final String PASSWORD = "Password.123";
    private final List<UserDto> USER_LIST = Arrays.asList(
            UserDto.builder().name("John").lastname("Doe").email("john@doe.com").build(),
            UserDto.builder().name("Jane").lastname("Doe").email("jane@doe.com").build(),
            UserDto.builder().name("John").lastname("Doe").email("john2@doe.com").build()
    );


    @Override
    public void run(ApplicationArguments args) throws Exception {
        insertUsers();
    }


    @Transactional
    public void insertUsers() {
        USER_LIST.stream().filter(u -> !isUserPresent(u.getEmail())).forEach(this::saveUser);
    }

    private void saveUser(UserDto u) {
        u.setPassword(bCryptPasswordEncoder.encode(PASSWORD));
        userRepository.save(Mapper.map(u, User.class));
    }

    private boolean isUserPresent(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
