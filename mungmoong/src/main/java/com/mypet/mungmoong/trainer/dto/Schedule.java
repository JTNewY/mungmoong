package com.mypet.mungmoong.trainer.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Schedule {

    private int no;
    private String userId;
    private int trainerNo;
    private String title;
    private String content;
    private Date scheduleDate;
    private Date regDate;
    private Date updDate;

}
