/**
 * 모든 응답 Dto가 추가되는 곳
 */

// 롬복 테스트용 프로그램

package com.aws.deploy.CRUDAWStest.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HelloResponseDto {

    private final String name;
    private final int amount;

}



