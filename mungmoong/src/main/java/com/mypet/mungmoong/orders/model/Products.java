package com.mypet.mungmoong.orders.model;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Products {
    public static final String TABLE_NAME = "products";
    private String id;
    private String name;
    private String category;
    private String categoryName;
    private String description;
    private String content;
    private int price;
    private int stock = 0;
    private Date createdAt;
    private Date updatedAt;

    MultipartFile thumbnail;
    String thumbnailId;
    List<MultipartFile> files;
}
