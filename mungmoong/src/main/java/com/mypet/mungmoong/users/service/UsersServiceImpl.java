package com.mypet.mungmoong.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mypet.mungmoong.pet.dto.Pet;
import com.mypet.mungmoong.pet.mapper.PetMapper;
import com.mypet.mungmoong.users.dto.LoginResponse;
import com.mypet.mungmoong.users.dto.SocialLoginRequest;
import com.mypet.mungmoong.users.dto.SocialUserResponse;
import com.mypet.mungmoong.users.dto.UserAuth;
import com.mypet.mungmoong.users.dto.UserJoinRequest;
import com.mypet.mungmoong.users.dto.UserSocial;
import com.mypet.mungmoong.users.dto.Users;
import com.mypet.mungmoong.users.mapper.UsersMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("userServiceImplForUsers")
public class UsersServiceImpl implements UsersService {

    private  UsersMapper userMapper;
    private PetMapper petMapper;
    private  PasswordEncoder passwordEncoder;
    

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    public UsersServiceImpl(UsersMapper userMapper, PetMapper petMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userMapper = userMapper;
        this.petMapper = petMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public boolean login(Users user) throws Exception {
        // 💍 토큰 생성
        String username = user.getUserId(); // 아이디
        String password = user.getPassword(); // 암호화되지 않은 비밀번호
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        // 토큰을 이용하여 인증
        Authentication authentication = authenticationManager.authenticate(token);

        // 인증 여부 확인
        boolean result = authentication.isAuthenticated();

        // 시큐리티 컨텍스트에 등록
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return result;
    }

    @Override
    public Users select(String username) throws Exception {
        Users user = userMapper.select(username);
        return user;
    }

    @Override
    public int join(Users user) throws Exception {
        String username = user.getUserId();
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password); // 🔒 비밀번호 암호화
        user.setPassword(encodedPassword);

        // 회원 등록
        int result = userMapper.join(user);

        if (result > 0) {
            // 회원 기본 권한 등록
            UserAuth userAuth = new UserAuth();
            userAuth.setUserId(username);
            userAuth.setAuth("ROLE_USER");
            result = userMapper.insertAuth(userAuth);

            // 펫 등록
            Pet pet = user.getPet();
            petMapper.insertPet(pet);
        }
        return result;
    }

    @Override
    public int update(Users user) throws Exception {
        return userMapper.update(user);
    }

    @Override
    public int insertAuth(UserAuth userAuth) throws Exception {
        return userMapper.insertAuth(userAuth);
    }

    @Override
    public List<Users> list() throws Exception {
        List<Users> usersList = userMapper.list();
        return usersList;
    }

    @Override
    public int delete(String userId) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Users findId(String name, String mail) throws Exception {
        Users user = userMapper.findId(name, mail);
        return user;
    }

    @Override
    public Users findPw(String userId, String mail) throws Exception {
        Users user = userMapper.findPw(userId, mail);
        return user;
    }

    @Override
    public int updatePassword(String userId, String mail, String password) throws Exception {
        return userMapper.updatePassword(userId, mail, password);
    }

    @Override
    public int roleUp(Users user) throws Exception {
        log.info("user : " + user);
        int result = userMapper.roleUp(user);
        log.info("result : " + result);

        if (result > 0) {
            log.info("권한이 " + user.getRole() + "으로 업데이트 됨!");
            UserAuth userAuth = new UserAuth();
            userAuth.setUserId(user.getUserId());
            userAuth.setAuth("ROLE_TRAINER");
            insertAuth(userAuth);
        }
        return result;
    }

    @Override
    public int insertSocial(UserSocial userSocial) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'insertSocial'");
    }

    @Override
    public UserSocial selectSocial(UserSocial userSocial) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'selectSocial'");
    }

    @Override
    public int updateSocial(UserSocial userSocial) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'updateSocial'");
    }

    @Override
    public Users selectBySocial(UserSocial userSocial) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'selectBySocial'");
    }

    @Override
    public SocialUserResponse getUserInfo(String accessToken) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserInfo'");
    }

    @Override
    public void joinUser(UserJoinRequest request) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'joinUser'");
    }

    @Override
    public LoginResponse doSocialLogin(SocialLoginRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'doSocialLogin'");
    }
}
