package com.aws.deploy.CRUDAWStest.domain.user;


import com.aws.deploy.CRUDAWStest.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING) // JPA DB 저장 시 Enum 값을 어떤 형태로 저장할 지 결정.
    // 기본적으로 int로 저장되지만, 문자열로 여기선 지정했다.
    @Column(nullable = false)
    private Role role; // Enum 클래스의 Role 따로 생성

    @Builder
    public User(String name, String email, String picture, Role role) {

        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name ;
        this.picture = picture;

        return this;

    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
