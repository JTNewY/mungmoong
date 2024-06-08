package com.mypet.mungmoong.orders.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Products {
    public static final String TABLE_NAME = "products";
    private String id;
    private int trainerNo;
    private String name;
    private String description;
    private String content;
    private int price;
    private Date createdAt;
    private Date updatedAt;

    MultipartFile thumbnail;
    String thumbnailId;
    List<MultipartFile> files;
}
