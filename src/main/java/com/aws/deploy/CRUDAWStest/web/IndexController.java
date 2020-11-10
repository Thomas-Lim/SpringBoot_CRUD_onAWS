package com.aws.deploy.CRUDAWStest.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    @GetMapping("/") // 머스태치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자를 자동으로 지정.
    // 이와함께 뷰 리졸버가 자동으로 처리하게 됨.
    // view Resolver는 URL 요청의 결과를 전달할 타입과 값을 지정하는 관리자 격으로 볼 수 있다.
    public String index() {
        return "index";
    }

    @GetMapping("/posts/save")   // 1. /posts/save을 호출하면
    public String postsSave() {
        return "posts-save";   // 2. posts-save.mustache가 호출된다.
    }
}
