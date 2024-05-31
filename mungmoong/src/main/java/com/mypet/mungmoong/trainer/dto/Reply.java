package com.mypet.mungmoong.trainer.dto;

import java.util.Date;
import lombok.Data;

//댓글
@Data
public class Reply {
    private int no;
    private int boardNo;
    private int parentNo;
    private String writer;
    private String content;
    private Date regDate;
    private Date updDate;

   // List<Reply> childList;

}