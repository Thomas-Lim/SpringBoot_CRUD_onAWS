
/**
 * Save, findAll 기능 테스트
 */

package com.aws.deploy.CRUDAWStest.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // 별다른 설정 없이 이걸 사용하면 H2 데이터베이스를 자동으로 실행해 줌.
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After // JUnit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정.
    // 보통 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용.
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder() // 테이블 posts에 insert/update 쿼리 실행
                // id가 존재하면 update, 없으면 insert 실행
                .title(title)
                .content(content)
                .author("jsdfjd@naver.com")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll(); // 테이블 posts에 있는 모든 데이터 조회. Posts 클래스를 리스트 형식으로 설정해서 조회함.

        // then
        Posts posts = postsList.get(0);
        Assertions.assertThat(posts.getTitle()).isEqualTo(title);
        Assertions.assertThat(posts.getContent()).isEqualTo(content);

        /**
         * Hibernate: drop table posts if exists
         * Hibernate: create table posts (id bigint generated by default as identity, author varchar(255), content TEXT not null, title varchar(500) not null, primary key (id))
         * main -> resources 에서 application.properties 내용을 spring.jpa.show_sql=true 수정시 콘솔창에 위와같은 내용이 뜸.
         * 기본적으로 H2 DB 문법으로, 이를 mysql 문법으로 바꿀 시 마찬가지로 해당 위치에서 내용 추가로 수정가능
         * spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
         * Hibernate: create table posts (id bigint not null auto_increment, author varchar(255), content TEXT not null, title varchar(500) not null, primary key (id)) engine=InnoDB 이렇게 보임
         */
    }

    /**
     * JPA Auditing 시간 관련 테스트 코드 메소드 추가
     */

    @Test
    public void BaseTimeEntity_등록 () {
        // given
        LocalDateTime now = LocalDateTime.of(2020,2,1,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>> createDate="+posts.getCreateDate()+", modifiedDate="+posts.getModifiedDate());

        Assertions.assertThat(posts.getCreateDate()).isAfter(now);
        Assertions.assertThat(posts.getModifiedDate()).isAfter(now);
    }
}