package com.mypet.mungmoong.trainer.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypet.mungmoong.trainer.dto.Career;
import com.mypet.mungmoong.trainer.mapper.CareerMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service        // 서비스 역할의 스프링 빈 등록
public class CareerServiceImpl implements CareerService {
    
    @Autowired
    private CareerMapper careerMapper;
    
    @Autowired
    private FileService fileService;

    @Override
    public List<Career> select(String userId) throws Exception {
        List<Career> careerList = careerMapper.select(userId);

        return careerList;
    }

    @Override
    public int insert(Career career) throws Exception {
        int result = careerMapper.insert(career);

        return result;
    }

    @Override
    public int update(Career career) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
