package com.mypet.mungmoong.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mypet.mungmoong.main.model.Basic;


@Mapper
public interface BasicMapper {

    public List<Basic> list() throws Exception;

    public Basic select(String id) throws Exception;

    public int insert(Basic basic) throws Exception;

    public int update(Basic basic) throws Exception;

    public int delete(String id) throws Exception;
    
}
