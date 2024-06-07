package com.mypet.mungmoong.trainer.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

import com.mypet.mungmoong.trainer.dto.Schedule;


@Mapper
public interface ScheduleMapper {

    // 날짜 등록
    public int insert(Schedule schedule) throws Exception;

    // 날짜 삭제
    public int delete(int trainerNo, Date date) throws Exception;

    
}
