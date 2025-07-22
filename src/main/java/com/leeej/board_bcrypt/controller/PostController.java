package com.leeej.board_bcrypt.controller;

import com.leeej.board_bcrypt.dto.PostRequestDto;
import com.leeej.board_bcrypt.dto.PostResponseDto;
import com.leeej.board_bcrypt.model.Post;
import com.leeej.board_bcrypt.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @GetMapping
    public List<PostResponseDto> listAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        return postService.listAll(pageable);
    }

    @GetMapping("/{id}")
    public PostResponseDto get(@PathVariable Long id) {
        return postService.getById(id);
    }

    @PostMapping
    public PostResponseDto create(@Valid @RequestBody PostRequestDto dto) {
        return postService.create(dto);
    }
}
