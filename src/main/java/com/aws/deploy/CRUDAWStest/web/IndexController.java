package com.aws.deploy.CRUDAWStest.web;

import com.aws.deploy.CRUDAWStest.config.auth.LoginUser;
import com.aws.deploy.CRUDAWStest.config.auth.dto.SessionUser;
import com.aws.deploy.CRUDAWStest.service.PostsService;
import com.aws.deploy.CRUDAWStest.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    // 반복되는 부분을 어노테이션으로 대신 함. private final HttpSession httpSession; // userName 사용할수 있게 하는 코드 추가

    @GetMapping("/") // 머스태치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자를 자동으로 지정.
    // 이와함께 뷰 리졸버가 자동으로 처리하게 됨.
    // view Resolver는 URL 요청의 결과를 전달할 타입과 값을 지정하는 관리자 격으로 볼 수 있다.
    // 과거 사용 구문. public String index() {  return "index";    }
    
    /**
     * 전체 조회화면 관련 내용 추가 수정
     */

    /**
     * index.mustache에서 userName을 model에 저장하는 코드 추가
     * 하지만 @LoginUser가 만들어졌으므로, 이것만 사용하면 어떤 컨트롤러든지 세션 정보를 가져올 수 있음.
     */


    public String index(Model model, @LoginUser SessionUser user) { // 서버 탬플릿 엔진에서 사용할 수 있는 객체 지정 가능.
        model.addAttribute("posts", postsService.findAllDesc());
        // 앞서 작성한 CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성
        // 즉, 로그인 성공 시 httpSession.getAttribute("user")에서 값을 가져올수 있음.
        // 반복되는 부분을 어노테이션으로 대신 함 SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) { // 세션에 저장된 값이 ㅣㅆ을 때만 model에서 userName으로 등록. 세션에 저장된 값이 없으면 model엔 아무런 값이 없는 상태여서 로그인 버튼이 보임.
            model.addAttribute("userName", user.getName());
        }
        return "index"; // index.mustache를 반환
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
