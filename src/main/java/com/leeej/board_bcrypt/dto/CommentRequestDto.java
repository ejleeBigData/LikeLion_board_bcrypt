package com.leeej.board_bcrypt.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentRequestDto {
    @NotBlank
    private String content;

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}