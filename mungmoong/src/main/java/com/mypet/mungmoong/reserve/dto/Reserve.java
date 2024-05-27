package com.mypet.mungmoong.reserve.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Reserve {
    private int date_no;
    private int order_no;
    private String user_id;
    private String trainer_id;
    private Date date_time;
    private Date date_day;
    private Date reg_date;
    private Date upd_date;
}
