package com.mypet.mungmoong.QnA.dto;

import java.util.Date;

import lombok.Data;

@Data
public class QnA {
    private int no;
    private String title;
    private String writer;
    private String content;
    private Date regDate;
    private Date updDate;
}
