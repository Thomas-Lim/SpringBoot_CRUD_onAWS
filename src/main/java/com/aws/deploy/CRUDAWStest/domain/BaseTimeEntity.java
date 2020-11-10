package com.aws.deploy.CRUDAWStest.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity 클래스들이 BBaseTimeEntity을 상속할 경우 필드들도 칼럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class) // 단순히 BaseTimeEntity 클래스에 Auditing 기능 포함.

public class BaseTimeEntity { // 모든 Entity의 상위 클래스가 되어 Entity들의 createdDate, modifiedDate를 자동으로 관리하는 역할을 한다.

    @CreatedDate // Entity가 생성되어 저장될 때 시간이 자동 저장됨.
    private LocalDateTime createDate;

    @LastModifiedDate // 조회한 Entity의 값을 변경할 때 시간이 자종 저장.
    private LocalDateTime modifiedDate;

    /**
     * 이 코드를 추가함으로서 앞으로 추가될 엔티티들은 더이상 등록일/수정일 고민할 필요가 없게 되었다.
     * BaseTimeEntity만 상속받으면 자동으로 해결되기 때문이다.
     */

}
