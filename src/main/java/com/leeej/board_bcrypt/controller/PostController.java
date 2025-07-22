package com.leeej.board_bcrypt.controller;

import com.leeej.board_bcrypt.dto.PostDeleteRequestDto;
import com.leeej.board_bcrypt.dto.PostRequestDto;
import com.leeej.board_bcrypt.dto.PostResponseDto;
import com.leeej.board_bcrypt.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
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

    @PutMapping("/{id}")
    public PostResponseDto update(@PathVariable Long id, @Valid @RequestBody PostRequestDto dto) {
        return postService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, @Valid @RequestBody PostDeleteRequestDto dto) {
        postService.delete(id, dto);
    }
}