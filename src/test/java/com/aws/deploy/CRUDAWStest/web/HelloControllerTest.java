package com.aws.deploy.CRUDAWStest.web;


// import org.junit.jupiter.api.Test; 이걸로 해주면 오류남
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class) // 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자 실행시킴. 여기선 그래서 SpringRunner 실행시킴.
// 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 함.
@WebMvcTest(controllers = HelloController.class) // 컨트롤러 사용시만 선언

public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈(Bean)을 주입 받음. 이어주는 역할
    private MockMvc mvc; // 웹 API를 테스트할 때 사용. 스프링 MVC 테스트의 시작점.

    @Test
    public void hello가_리턴된다() throws Exception { // 테스트 구문이므로 한글로 작성해도 무방
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) // 위의 get 결과를 검증. 상태메시지 띄우는역할
                .andExpect(content().string(hello)); // 응답 본문의 내용을 검증. hello를 리턴하므로 이게 맞는지 검증.
    }

    /**
     * helloDto 롬복 기능 테스트 추가
     */
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount",String.valueOf(amount)))  // 요청된 파라미터를 설정하며, 스트링값만 허용되므로 변환 필요.
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

    }
}


