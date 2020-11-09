package com.aws.deploy.CRUDAWStest.service;

import com.aws.deploy.CRUDAWStest.domain.posts.Posts;
import com.aws.deploy.CRUDAWStest.domain.posts.PostsRepository;
import com.aws.deploy.CRUDAWStest.web.dto.PostsResponseDto;
import com.aws.deploy.CRUDAWStest.web.dto.PostsSaveRequestDto;
import com.aws.deploy.CRUDAWStest.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {

        return postsRepository.save(requestDto.toEntity()).getId();
    }

    /**
     * 사용자 업데이트 부분
     * 영속성 상태가 유지되며 트랜제견이 끝나는 ㅣ점에 해당 테이블에 변경분을 반영한다.
     * 이 개념을 더티 체킹 이라고 한다.
     */

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 업습니다. id="+ id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 업습니다. id="+ id));

        return new PostsResponseDto(entity);
    }
}


