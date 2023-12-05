package com.emlakjet.demo.service.impl;

import com.emlakjet.demo.common.exception.EmlakjetException;
import com.emlakjet.demo.common.util.Mapper;
import com.emlakjet.demo.dto.UserDto;
import com.emlakjet.demo.entity.User;
import com.emlakjet.demo.repository.UserRepository;
import com.emlakjet.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.emlakjet.demo.common.i18n.TranslationUtil.USER_NOT_FOUND;
import static com.emlakjet.demo.common.i18n.Translator.toLocale;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return Mapper.map(user.get(), UserDto.class);
        }

        log.error("User email {} not found in database!", email);
        throw new EmlakjetException(toLocale(USER_NOT_FOUND));
    }
}
