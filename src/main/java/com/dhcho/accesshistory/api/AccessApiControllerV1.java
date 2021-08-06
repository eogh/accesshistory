package com.dhcho.accesshistory.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/access")
public class AccessApiControllerV1 {

    // TODO: 출입이력 insert / delete
    // TODO: 출입이력 조회 (기간별로, Paging/슬라이더 처리)
    // TODO: member/team별 출입통계 조회(어느쪽 클래스에 정의할지는 고민)


}
