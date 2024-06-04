package com.mypet.mungmoong.main.service;

import java.util.List;

import com.mypet.mungmoong.main.model.Basic;


public interface BasicService {

    public List<Basic> list() throws Exception;

    public Basic select(String id) throws Exception;

    public int insert(Basic basic) throws Exception;

    public int update(Basic basic) throws Exception;

    public int delete(String id) throws Exception;
    
}
