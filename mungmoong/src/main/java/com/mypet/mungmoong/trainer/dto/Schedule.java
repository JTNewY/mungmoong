package com.mypet.mungmoong.trainer.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Schedule {

    private int no;
    private int trainerNo;
    private String title;
    private String content;
    private Date date;
    private Date regDate;
    private Date updDate;

}
