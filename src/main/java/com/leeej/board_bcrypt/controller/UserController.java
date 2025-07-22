package com.leeej.board_bcrypt.controller;

import com.leeej.board_bcrypt.dto.UserDto;
import com.leeej.board_bcrypt.model.User;
import com.leeej.board_bcrypt.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public User register(@Valid @RequestBody UserDto dto) {
        return userService.register(dto);
    }

}
