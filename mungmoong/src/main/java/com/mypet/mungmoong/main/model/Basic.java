package com.mypet.mungmoong.main.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Basic {
    private String id;
    private String name;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}
