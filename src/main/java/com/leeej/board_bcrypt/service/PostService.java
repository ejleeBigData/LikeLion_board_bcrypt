package com.leeej.board_bcrypt.service;

import com.leeej.board_bcrypt.dto.PostRequestDto;
import com.leeej.board_bcrypt.dto.PostResponseDto;
import com.leeej.board_bcrypt.model.Post;
import com.leeej.board_bcrypt.model.User;
import com.leeej.board_bcrypt.repository.PostRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public List<PostResponseDto> listAll(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);

        return posts.stream()
                .map(PostResponseDto::fromEntity)
                .toList();
    }

    public PostResponseDto getById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 Post를 없음!!!!"));

        return PostResponseDto.fromEntity(post);
    }

    public PostResponseDto create(PostRequestDto dto) {
        User user = userService.login(dto.getUsername(), dto.getPassword());

        Post post = new Post();
        post.setAuthor(user);
        post.setTitle(dto.getTitle());
        if( dto.getContent() != null && !dto.getContent().isBlank() ) post.setContent(dto.getContent());

        return PostResponseDto.fromEntity(postRepository.save(post));
    }

    public void delete(Long id, PostRequestDto dto) {

    }
}
