package com.mypet.mungmoong.users.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mypet.mungmoong.users.model.Address;


@Mapper
public interface AddressMapper {

    // 목록 조회
    List<Address> list() throws Exception;
    
    // 단일 조회
    Address select(String id) throws Exception;
    
    // 등록
    int insert(Address address) throws Exception;
    
    // 수정
    int update(Address address) throws Exception;
    
    // 삭제
    int delete(String id) throws Exception;

    // 목록 조회 - userId
    List<Address> listByUserId(String userId) throws Exception;
}