package com.mypet.mungmoong.pet.dto;

import java.util.Date;

public class reservation {
    private int reservationId;
    private String userId;
    private Date reservationDate;
    private String status;

    // 생성자, 게터 및 세터 생략

    // 예약 ID
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    // 사용자 ID
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // 예약 일자
    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    // 상태
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
