package com.mypet.mungmoong.QnA.dto;

import java.util.Date;

import lombok.Data;

@Data
public class QnA {
    private int qnaNo;
    private String qnaTitle;
    private String qnaWriter;
    private String qnaContent;
    private Date regDate;
    private Date updDate;
}
