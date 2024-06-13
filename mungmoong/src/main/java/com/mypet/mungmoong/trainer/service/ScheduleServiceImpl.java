package com.mypet.mungmoong.trainer.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypet.mungmoong.trainer.dto.Schedule;
import com.mypet.mungmoong.trainer.mapper.ScheduleMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ScheduleServiceImpl implements ScheduleService  {

    @Autowired
    private ScheduleMapper scheduleMapper;
    
    @Override
    public List<Schedule> select(int trainerNo) throws Exception {
        List<Schedule> scheduleList = scheduleMapper.select(trainerNo);
        return scheduleList;
    }


     @Override
     public int insert(Schedule schedule) throws Exception {
         int result = scheduleMapper.insert(schedule);

         return result;
     }

     @Override
     public int delete(int trainerNo, Date date) throws Exception {
         int result = scheduleMapper.delete(trainerNo, date);

         return result;
     }


    @Override
    public int update(Schedule schedule) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int deleteByNo(int no) throws Exception {
        int result = scheduleMapper.deleteByNo(no);
        return result;
    }


    
 }
