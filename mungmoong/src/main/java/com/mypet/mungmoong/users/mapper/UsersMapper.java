package com.mypet.mungmoong.users.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import org.springframework.stereotype.Component;

import com.mypet.mungmoong.users.dto.UserAuth;
import com.mypet.mungmoong.users.dto.Users;

@Mapper
@Component("usersMapperForUsers")
public interface UsersMapper {

    // 로그인
    public Users login(String userId) throws Exception;

    // public List<Admin> list()

    // 회원 조회
    public Users select(String userId) throws Exception;

    // 회원 가입
    public int join(Users user) throws Exception;

    // 회원 수정
    public int update(Users user) throws Exception;

    // 회원 권한 등록
    public int insertAuth(UserAuth userAuth) throws Exception;

    // 어드민 회원 조회
    public List<Users> list() throws Exception;

    // 게시글 삭제
    public int delete(String userId) throws Exception;

}