package com.aws.deploy.CRUDAWStest.web;


import com.aws.deploy.CRUDAWStest.service.PostsService;
import com.aws.deploy.CRUDAWStest.web.dto.PostsListResponseDto;
import com.aws.deploy.CRUDAWStest.web.dto.PostsResponseDto;
import com.aws.deploy.CRUDAWStest.web.dto.PostsSaveRequestDto;
import com.aws.deploy.CRUDAWStest.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // final이 선언된 모든 필드를 인자값으로 하는 생성자를 이것이 대신 생성해 줌.
// 해당 클래스의 의존성 관계가 변경될 때마다 생성자 코드를 계속해서 수정하는 번거로움을 해결할 수 있다.
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {

        return postsService.save(requestDto);
    }

    /**
     * 수정, 조회기능 만들기
     *
     *
     *     */
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {

        return postsService.findById(id);
    }


//    @GetMapping("/api/v1/posts/list")
//    public List<PostsListResponseDto> findAll() {
//
//        return postsService.findAllDesc();
//    }
}



