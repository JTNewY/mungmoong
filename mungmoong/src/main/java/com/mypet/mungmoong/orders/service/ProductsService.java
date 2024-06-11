package com.mypet.mungmoong.orders.service;

import java.util.List;

import com.mypet.mungmoong.orders.dto.Products;


public interface ProductsService {
    public List<Products> list() throws Exception;

    public Products select(String id) throws Exception;

    public int insert(Products products) throws Exception;

    public int adminInsert(Products products) throws Exception;

    public int update(Products prodcuts) throws Exception;

    public int delete(String deleteIdList) throws Exception;

    // 썸네일 업로드
    public void updloadThumbnail(Products products) throws Exception;

    public List<Products> adminList() throws Exception;

    public int adminUpdate(Products products) throws Exception;

    public int adminDelete(String id) throws Exception;
}
