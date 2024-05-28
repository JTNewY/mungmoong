package com.mypet.mungmoong.users.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mypet.mungmoong.users.dto.UserAuth;
import com.mypet.mungmoong.users.dto.Users;

@Mapper
public interface UsersMapper {

    // 로그인
    public Users login(String username) throws Exception;

    // public List<Admin> list()

    // 회원 조회
    public Users select(String username) throws Exception;

    // 회원 가입
    public int join(Users user) throws Exception;

    // 회원 수정
    public int update(Users user) throws Exception;

    // 회원 권한 등록
    public int insertAuth(UserAuth userAuth) throws Exception;

 
}