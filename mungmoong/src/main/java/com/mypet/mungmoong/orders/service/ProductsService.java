package com.mypet.mungmoong.orders.service;

import java.util.List;

import com.mypet.mungmoong.orders.model.Products;


public interface ProductsService {
    public List<Products> list() throws Exception;

    public Products select(String id) throws Exception;

    public int insert(Products prodcuts) throws Exception;

    public int update(Products prodcuts) throws Exception;

    public int delete(String deleteIdList) throws Exception;

    // 썸네일 업로드
    public void updloadThumbnail(Products products) throws Exception;
}