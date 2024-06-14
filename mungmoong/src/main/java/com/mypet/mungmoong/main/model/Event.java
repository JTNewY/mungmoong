package com.mypet.mungmoong.main.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    private int no;
    private String title;
    private Date start;
    private Date end;
    private String description;
    private String color = "blue";

    public Event(String title, String description, Date date) {
        this.title = title;
        this.description = description;
        this.start = date;
        this.end = date;
    }

    public Event(int no, String title, String description, Date date) {
        this.no = no;
        this.title = title;
        this.description = description;
        this.start = date;
        this.end = date;
    }
}