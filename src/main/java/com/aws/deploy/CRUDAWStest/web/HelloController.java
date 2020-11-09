// web 패키지에 컨트롤러와 관련된 모든 클래스들이 있음

package com.aws.deploy.CRUDAWStest.web;

import com.aws.deploy.CRUDAWStest.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어준다.
public class HelloController {

    @GetMapping("/hello") // Get의 요청을 받을 수 있는 API를 만들어 준다.
    // /hello로 요청이 오면 문자열 hello를 반환한다.
    public String hello() {

        return "hello";
    }

    /**
     * 롬복의 ResponseDto가 사용되도록 코드 추가
     */
    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, // 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션.
                                     //외부 name(@RequestParam 으로 넘긴 파라미터를 메소드 파라미터 name(String name)에 저장하게 됨
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}



