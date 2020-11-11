package com.aws.deploy.CRUDAWStest.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    /**
     * 단순히 인터페이스 생성 후 JpaRepository<Entity 클래스, PK 타입>  상속하면 기본적인 CRUD 메소드가 자동으로 생성된다.
     * 주의점은, Entity 클래스와 기본 Entity Repository(이 파일처럼)은 함께 위치해야 한다. 아주 밀접한 관계이기 때문.
     */

    // 머스테치 수정 후 게시글번호, 제목, 작성자, 최종수정일을 연결하기 위해서 아래 쿼리로 수정

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    // SpringDataJap에서 제공하지 않는 메소드는 위처럼 쿼리로 작성해도 된다.
    List<Posts> findAllDesc();

    /**
     * 통상적으로 조회는 querydsl을 사용. 등록/수정/삭제는 SpringDatajpa 사용.
     */
}