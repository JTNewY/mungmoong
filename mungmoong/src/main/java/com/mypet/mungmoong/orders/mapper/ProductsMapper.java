package com.mypet.mungmoong.orders.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mypet.mungmoong.orders.model.Products;


@Mapper
public interface ProductsMapper {
    public List<Products> list() throws Exception;

    public Products select(String id) throws Exception;

    public int insert(Products prodcuts) throws Exception;

    public int update(Products prodcuts) throws Exception;

    public int delete(String deleteIdList) throws Exception;
}
