package com.mypet.mungmoong.board.dto;

import lombok.Data;
import java.util.Date;

@Data
public class Board {
    private int boardNo;
    private String title;
    private String content;
    private String fileName;
    private String userId;
    private Date regDate;
    private Date updDate;
    private Integer qnaType;
    private Boolean answered; 

}
