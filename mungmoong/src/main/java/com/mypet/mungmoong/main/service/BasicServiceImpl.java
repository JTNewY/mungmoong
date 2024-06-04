package com.mypet.mungmoong.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypet.mungmoong.main.mapper.BasicMapper;
import com.mypet.mungmoong.main.model.Basic;


@Service
public class BasicServiceImpl implements BasicService {

    @Autowired
    private BasicMapper basicMapper;

    @Override
    public List<Basic> list() throws Exception {
        return basicMapper.list();
    }

    @Override
    public Basic select(String id) throws Exception {
        return basicMapper.select(id);
    }

    @Override
    public int insert(Basic basic) throws Exception {
        int result = basicMapper.insert(basic);
        return result;
    }

    @Override
    public int update(Basic basic) throws Exception {
        int result = basicMapper.update(basic);
        return result;
    }

    @Override
    public int delete(String id) throws Exception {
        int result = basicMapper.delete(id);
        return result;
    }
    
}
