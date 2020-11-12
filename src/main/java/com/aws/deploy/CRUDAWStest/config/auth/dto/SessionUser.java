/**
 * 앞서 만든 SessionUser 정보와 구분할 필요가 있다.
 * SessionUser에는 인증된 사용자 정보만 필요하다.
 * 이렇게 직렬화 기능을 가진 세션 Dto를 하나 추가로 만드는 것이 이후 운영 및 유지보수 때 많은 도움이 된다.
 */

package com.aws.deploy.CRUDAWStest.config.auth.dto;


import com.aws.deploy.CRUDAWStest.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
