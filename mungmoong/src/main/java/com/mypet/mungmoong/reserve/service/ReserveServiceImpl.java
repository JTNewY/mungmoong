package com.mypet.mungmoong.reserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypet.mungmoong.reserve.dto.Reserve;
import com.mypet.mungmoong.reserve.mapper.ReserveMapper;

@Service
public class ReserveServiceImpl implements ReserveService {
    
    @Autowired
    private ReserveMapper reserveMapper;

    @Override
    public List<Reserve> list() throws Exception {
       List<Reserve> reservesList = reserveMapper.list();
       return reservesList;
       
    }

    @Override
    public Reserve select(int date_no) throws Exception {
      Reserve reserve = reserveMapper.select(date_no);
    return reserve;
    }

    @Override
    public Integer insert(Reserve reserve) throws Exception {
     int result = reserveMapper.insert(reserve);
    return result;
    }

    @Override
    public Integer update(Reserve reserve) throws Exception {
       int result = reserveMapper.update(reserve);
       return result;
    }

    @Override
    public Integer delete(int date_no) throws Exception {
   int result = reserveMapper.delete(date_no);
    return result;
    }

    @Override
    public List<Reserve> listByUser() throws Exception {
        return reserveMapper.listByUser();
    }
}
