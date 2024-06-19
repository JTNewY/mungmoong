package com.mypet.mungmoong.users.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mypet.mungmoong.trainer.dto.Option;
import com.mypet.mungmoong.trainer.dto.Page;
import com.mypet.mungmoong.users.dto.LoginResponse;
import com.mypet.mungmoong.users.dto.SocialLoginRequest;
import com.mypet.mungmoong.users.dto.SocialUserResponse;
import com.mypet.mungmoong.users.dto.UserAuth;
import com.mypet.mungmoong.users.dto.UserJoinRequest;
import com.mypet.mungmoong.users.dto.UserSocial;
import com.mypet.mungmoong.users.dto.Users;

@Service
public interface UsersService {

    // 로그인
    public boolean login(Users user) throws Exception;

    // 조회
    public Users select(String id) throws Exception;

    // 회원 가입
    public int join(Users user) throws Exception;

    // 회원 수정
    public int update(Users user) throws Exception;

    // 마이페이지 정보 수정
    public int Myupdate(Users user) throws Exception;

    // 회원 권한 등록
    public int insertAuth(UserAuth userAuth) throws Exception;

    // 어드민 회원 조회
    public List<Users> list(Page page, Option option) throws Exception;

    // 게시글 삭제
    public int delete(String userId) throws Exception;

    // 이름과 이메일로 아이디 찾기
    public Users findId(String name,String mail) throws Exception;

    // 아이디와 이메일로 비밀번호 찾기
    public Users findPw(String userId, String mail) throws Exception;

    public int updatePassword(String userId, String mail, String password) throws Exception;
        
    // 관리자 회원 권한 업데이트
    public int roleUp(Users user) throws Exception;         /* role 권한을 업데이트 하기 위한 userMapper */

    // 소셜 회원 가입
    public int insertSocial(UserSocial userSocial) throws Exception;
    
    // 소셜 회원 조회
    public UserSocial selectSocial(UserSocial userSocial) throws Exception;
    
    // 소셜 회원 수정
    public int updateSocial(UserSocial userSocial) throws Exception;
    
    // 소셜 정보로 회원 조회
    public Users selectBySocial(UserSocial userSocial) throws Exception;

    public SocialUserResponse getUserInfo(String accessToken);

    public void joinUser(UserJoinRequest request) throws Exception;

    public LoginResponse doSocialLogin(SocialLoginRequest request);
}
