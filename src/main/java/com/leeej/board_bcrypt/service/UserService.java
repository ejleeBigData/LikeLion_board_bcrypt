package com.leeej.board_bcrypt.service;

import com.leeej.board_bcrypt.dto.UserDto;
import com.leeej.board_bcrypt.model.User;
import com.leeej.board_bcrypt.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public User register(UserDto dto) {
        if(userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("이미 등록된 회원.");
        }

        String hash = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(10));
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPasswordHash(hash);

        return userRepository.save(user);
    }
}
