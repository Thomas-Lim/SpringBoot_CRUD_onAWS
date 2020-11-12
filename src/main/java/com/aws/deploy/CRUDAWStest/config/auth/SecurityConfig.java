package com.aws.deploy.CRUDAWStest.config.auth;

import com.aws.deploy.CRUDAWStest.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  // 스프링 시큐리티 설정등을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // h2-console 화면 사용위해 해당 옵션들 disable
                .csrf().disable()
                .headers().frameOptions().disable()

                .and()
                    .authorizeRequests() // URL별 권한 관리 옵션 시작
                    // 권한 관리 대상 지정 옵션. URL, HTTP 메소드 별로 관리 가능
                    .antMatchers("/", "/css/**", "/images/**",
                            "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated() // 설정된 값들 이외 나머지 URL들. 여기서는 auth...()을 추가해 나머지 URL들은 모두 인증된 사용자들에게만 허용.

                .and()
                    .logout()
                        .logoutSuccessUrl("/") // 로그아웃성공시 / 주소로 이동

                .and()
                    .oauth2Login()
                        .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당.
                            .userService(customOAuth2UserService); // 소셜 로그인 성공 후 리소스 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시.

    }
}
