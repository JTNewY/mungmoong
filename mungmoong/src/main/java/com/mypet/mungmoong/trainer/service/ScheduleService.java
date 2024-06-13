package com.mypet.mungmoong.trainer.service;

import java.util.Date;
import java.util.List;

import com.mypet.mungmoong.trainer.dto.Schedule;

public interface ScheduleService {

    // 트레이너 번호로 일정 조회
    List<Schedule> select(int trainerNo) throws Exception;

    // 날짜 등록
    public int insert(Schedule schedule) throws Exception;
    // 날짜 수정
    public int update(Schedule schedule) throws Exception;

    // 날짜 삭제
    public int delete(int trainerNo, Date date) throws Exception;

    // 날짜 삭제 번호로 삭제
    public int deleteByNo(int no) throws Exception;
    
}
