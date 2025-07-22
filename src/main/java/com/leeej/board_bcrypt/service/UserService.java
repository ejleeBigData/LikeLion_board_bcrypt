package com.leeej.board_bcrypt.service;

import com.leeej.board_bcrypt.dto.UserRequestDto;
import com.leeej.board_bcrypt.dto.UserResponseDto;
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

    public UserResponseDto register(UserRequestDto dto) {
        if(userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("이미 등록된 회원.");
        }

        String hash = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(10));
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPasswordHash(hash);

        return UserResponseDto.fromEntity(userRepository.save(user));
    }

    public User login(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("없는 유저!"));

        if(!BCrypt.checkpw(rawPassword, user.getPasswordHash())) {
            throw new RuntimeException("비밀번호 다름!");
        }

        return user;
    }
}
