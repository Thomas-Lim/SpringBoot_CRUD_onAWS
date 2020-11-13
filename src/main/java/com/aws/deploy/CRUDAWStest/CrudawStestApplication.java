/**
 * 앞으로 만들 프로젝트의 메인 클래스.
 */

package com.aws.deploy.CRUDAWStest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaAuditing // JPA Auditing 시간관련 활성화

/**
 * 테스트 코드 실행을위해 해당 기능 제거
 * // EnableJpaAuditing이 @SpringBootApplication과 함께 있다보니 @WebMvcTest에서도 스캔하게 되었다.
 * // 그래서 이를 방지하기 위해 @EnableJpaAuditing과 @SpringBootApplication을 분리한다.
 *
 * 이것은 config.JapConfig를 생성해 @EnableJpaAudiㅇting을 추가하는 것으로 해결한다.
 */
@SpringBootApplication
public class CrudawStestApplication {

	public static void main(String[] args) {

		SpringApplication.run(CrudawStestApplication.class, args);

		// 내장 WAS를 실행한다. 톰캣을 설치할 필요가 없고, 스프링 부트로 만들어진 Jar파일로 실행된다.
		// 언제 어디서나 같은 환경에서 스프링 부트를 배포할 수 있으므로 내장 WAS를 사용하는 것을 권장한다.

	}

}
