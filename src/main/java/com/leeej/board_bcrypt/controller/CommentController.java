package com.leeej.board_bcrypt.controller;

import com.leeej.board_bcrypt.dto.CommentRequestDto;
import com.leeej.board_bcrypt.dto.CommentResponseDto;
import com.leeej.board_bcrypt.dto.UserRequestDto;
import com.leeej.board_bcrypt.model.Comment;
import com.leeej.board_bcrypt.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public CommentResponseDto create(
            @PathVariable Long postId,
            @Valid @RequestBody CommentRequestDto dto
    ) {
        return commentService.create(postId, dto);
    }

    @PutMapping("/comments/{id}")
    public CommentResponseDto update(
            @PathVariable Long id,
            @Valid @RequestBody CommentRequestDto dto
    ) {
        return commentService.update(id, dto);
    }

    @DeleteMapping("/comments/{id}")
    public void delete(@PathVariable Long id, @Valid @RequestBody UserRequestDto dto) {
        commentService.delete(id, dto);
    }
}
