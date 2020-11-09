/**
 * 앞으로 만들 프로젝트의 메인 클래스.
 */

package com.aws.deploy.CRUDAWStest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CrudawStestApplication {

	public static void main(String[] args) {

		SpringApplication.run(CrudawStestApplication.class, args);

		// 내장 WAS를 실행한다. 톰캣을 설치할 필요가 없고, 스프링 부트로 만들어진 Jar파일로 실행된다.
		// 언제 어디서나 같은 환경에서 스프링 부트를 배포할 수 있으므로 내장 WAS를 사용하는 것을 권장한다.

	}

}
