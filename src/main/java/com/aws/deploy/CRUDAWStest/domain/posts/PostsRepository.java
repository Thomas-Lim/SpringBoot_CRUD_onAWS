package com.aws.deploy.CRUDAWStest.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    /**
     * 단순히 인터페이스 생성 후 JpaRepository<Entity 클래스, PK 타입>  상속하면 기본적인 CRUD 메소드가 자동으로 생성된다.
     * 주의점은, Entity 클래스와 기본 Entity Repository(이 파일처럼)은 함께 위치해야 한다. 아주 밀접한 관계이기 때문.
     */
}