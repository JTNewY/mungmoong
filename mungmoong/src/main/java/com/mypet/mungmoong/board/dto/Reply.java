package com.mypet.mungmoong.board.dto;
import java.util.Date;

import lombok.Data;
// 댓글
@Data
public class Reply {

    private int no;
    private int parentNo;
    private String parentTable;
    private String writer;
    private String userId;
    private String content;
    private Date regDate;
    private Date updDate;

}
