package com.aws.deploy.CRUDAWStest.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() { // 테스트 코드에는 이 두줄이 중요함.

        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name); // isEqualTo는 assertj의 동등 비교 메소드.
        assertThat(dto.getAmount()).isEqualTo(amount); // assertThat에 있는 값과 isEqualTo의 값을 비교해서 같을 때만 성공.

    }
}
