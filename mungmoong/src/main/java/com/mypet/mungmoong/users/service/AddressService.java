package com.mypet.mungmoong.users.service;

import java.util.List;

import com.mypet.mungmoong.users.model.Address;


public interface AddressService {

    // 목록 조회
    public List<Address> list() throws Exception;

    // 단일 조회
    public Address select(String id) throws Exception;

    // 등록
    public int insert(Address address) throws Exception;

    // 수정
    public int update(Address address) throws Exception;

    // 삭제
    public int delete(String id) throws Exception;

    // 목록 조회 - userId
    public List<Address> listByUserId(String userId) throws Exception;
}