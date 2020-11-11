package com.aws.deploy.CRUDAWStest.service;

import com.aws.deploy.CRUDAWStest.domain.posts.Posts;
import com.aws.deploy.CRUDAWStest.domain.posts.PostsRepository;
import com.aws.deploy.CRUDAWStest.web.dto.PostsListResponseDto;
import com.aws.deploy.CRUDAWStest.web.dto.PostsResponseDto;
import com.aws.deploy.CRUDAWStest.web.dto.PostsSaveRequestDto;
import com.aws.deploy.CRUDAWStest.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        postsRepository.delete(posts); // JpaRepository에서 delete 메소드를 지원한다.
        // 엔티티를 파라미터로 삭제할 수도 있고, deleteById 메소드를 이용해 id로 삭제할 수도 있다.
        // 존재여부 확인을 위해 엔티티 조회 후 싹 삭제
    }

    /**
     * 이 delete 메소드를 PostsApiController가 사용하도록 PostsApiController에 Delete 코드를 추가한다.
     */


    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    // 인터페이스에 Query 추가 후 조회 읽기 전용 내용 추가
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() { // PostsListResponseDto 클래스가 없으므로 클래스 생성
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // 람다식
                // 포스트의 스트림을 맵을 통해 ..Dto로 변환 후 List로 반환하는 메소드임.
                .collect(Collectors.toList());
    }
}


