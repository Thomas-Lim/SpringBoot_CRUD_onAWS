/**
 * @LoginUser를 사용하기 위한 환경구성에 쓰임.
 */

package com.aws.deploy.CRUDAWStest.config.auth;

import com.aws.deploy.CRUDAWStest.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) { // 서포트츠파라미터는 컨트롤러 메서드의 특정 파라미터를 지원하는지 판단함.
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

        return isLoginUserAnnotation && isUserClass;
    }

    @Override // 파라미터에 전달할 객체를 생성하는데, 여기서는 세션에 객체를 가져옴.
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("user");
    }
}
