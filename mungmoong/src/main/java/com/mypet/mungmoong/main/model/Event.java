package com.mypet.mungmoong.main.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    private String title;
    private Date start;
    private Date end;
    private String description;
    private String color = "blue";
    private String backgroundColor = "#FFE0B2";

    public Event(String title, String description, Date date) {
        this.title = title;
        this.description = description;
        this.start = date;
        this.end = date;
    }
}