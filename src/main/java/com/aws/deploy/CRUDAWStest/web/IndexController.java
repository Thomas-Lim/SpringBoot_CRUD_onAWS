package com.aws.deploy.CRUDAWStest.web;

import com.aws.deploy.CRUDAWStest.service.PostsService;
import com.aws.deploy.CRUDAWStest.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/") // 머스태치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자를 자동으로 지정.
    // 이와함께 뷰 리졸버가 자동으로 처리하게 됨.
    // view Resolver는 URL 요청의 결과를 전달할 타입과 값을 지정하는 관리자 격으로 볼 수 있다.
    // 과거 사용 구문. public String index() {  return "index";    }
    
    /**
     * 전체 조회화면 관련 내용 추가 수정
     */

    public String index(Model model) { // 서버 탬플릿 엔진에서 사용할 수 있는 객체 지정 가능.
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";  // 여기서는 postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달함.
    }    

    @GetMapping("/posts/save")   // 1. /posts/save을 호출하면
    public String postsSave() {

        return "posts-save";   // 2. posts-save.mustache가 호출된다.
    }

    /**
     * update 수정 기능 추가 수정
     */

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update"; // posts-update.mustache 파일을 얘기하는 것
    }

}
