
/**
 * Domain은 비즈니스 로직을 처리하는 부분
 * 꼭 Service 단에서 비즈니스 로직을 처리하지 않아도 된다.
 * Service 단에서 처리하는 방식을 '트랜잭션 스크립트' 라고 한다.
 * 트랜잭션 스크립트 방식은 내부의 모든 코드가 얽혀 있음
 * 반면 Domain 모델에서 처리 시 각 코드가 각자의 역할을 해냄.
 */

package com.aws.deploy.CRUDAWStest.domain.posts;


import com.aws.deploy.CRUDAWStest.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*; // Entity와 연결되는 것 !!!!

/**
 * 어노테이션 순서는 주요 어노테이션을 클래스에 가깝게 둔다.
 */
@Getter  // 롬복의 어노테이션. 클래스 내 모든 필드의 Getter 메소드를 자동 생성 (필수는 아니다)
@NoArgsConstructor // 롬복의 어노테이션. 기본 생성자 자동 추가. public Posts() {}와 같은 표과(필수는 아니다)

@Entity // JPA의 어노테이션. 테이블과 링크될 클래스임을 나타냄

/**
 * 게터는 있는데 세터는 없는 이유는, 엄청 복잡해져서임
 * Entity 클래스에서는 절대 Setter 메소드를 만들지 않음.
 * 그래서 생성자를 통해 최종값을 채운 후 DB에 삽입하는 것이다.
 * 값 변경 필요시 해당 이벤트에 맞는 public 메소드를 호출하여 변경한다.
 * 여기서는 생성자 대신에 @Builder를 통해 제공되는 빌더 클래스를 사용한다.
 */

public class Posts extends BaseTimeEntity { // 엔티티 시간 후 변경된 부분.
    @Id // 해당 테이블의 PK 필드.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성규칙. 스프링 부터 2.0 부터 필수가 되었음.
    private Long id;

    @Column(length = 500, nullable = false) // 컬럼어노테이션은, 기본값 외에 추가로 변경 필요 옵션 있을 시 사용함.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    // 여기서는 Column의 기본값을 사용하므로 별도의 선언 없음.
    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;

    }

    /**
     * 사용자 업데이트 부분
     */

    public void update(String title, String content)  {
        this.title = title;
        this.content = content;
    }

//    public class Posts extends BaseTimeEntity {
//
//    }
}