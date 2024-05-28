package com.mypet.mungmoong.trainer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 검색 옵션
 * - keyword : 검색어
 * - 옵션 코드  : code
    - 전체      : 0
    - 제목      : 1
    - 내용      : 2
    - 제목+내용 : 3
    - 작성자    : 4 
 */
@Data
@AllArgsConstructor
public class Option {
    String keyword;
    int code;

    public Option() {
        this.keyword = "";
        // 키워드가 비어있을 때, 전체 조회!
    }


}
