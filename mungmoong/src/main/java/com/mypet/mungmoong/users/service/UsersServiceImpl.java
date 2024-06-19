package com.mypet.mungmoong.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mypet.mungmoong.pet.dto.Pet;
import com.mypet.mungmoong.pet.mapper.PetMapper;
import com.mypet.mungmoong.trainer.dto.Option;
import com.mypet.mungmoong.trainer.dto.Page;
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

    private UsersMapper userMapper;
    private PetMapper petMapper;
    private PasswordEncoder passwordEncoder;

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

        // ############################## 06-14 수정 ##############################
        // 계정 활성화 설정
        user.setEnabled(1); // 수정된 부분
        // #########################################################################


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

   
    // 마이페이지 정보수정
    @Override
    public int Myupdate(Users user) throws Exception {
    // 데이터베이스에서 현재 사용자 정보를 가져옴
    Users currentUser = userMapper.select(user.getUserId());

    if (currentUser == null) {
        throw new Exception("User not found");
    }

    // 현재 비밀번호와 입력된 비밀번호가 다른 경우에만 비밀번호를 해시 형태로 변환
    if (user.getPassword() != null && !user.getPassword().isEmpty() && 
        !passwordEncoder.matches(user.getPassword(), currentUser.getPassword())) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    } else {
        // 비밀번호가 변경되지 않았다면 현재 비밀번호를 유지
        user.setPassword(currentUser.getPassword());
    }

    return userMapper.update(user);
}
    

    @Override
    public int insertAuth(UserAuth userAuth) throws Exception {
        return userMapper.insertAuth(userAuth);
    }

    @Override
    public List<Users> list(Page page, Option option) throws Exception {
        List<Users> usersList = userMapper.list(page, option);
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
    
// ############################## 06-14 코드 수정 ##############################
    @Override
    public int updatePassword(String userId, String mail, String password) throws Exception {
        String hashedPassword = passwordEncoder.encode(password); // 비밀번호 해싱
        return userMapper.updatePassword(userId, mail, hashedPassword); // 해시된 비밀번호 저장
    }

// ##############################################################################

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
        throw new UnsupportedOperationException("Unimplemented method 'getUserInfo'");
    }

    @Override
    public void joinUser(UserJoinRequest request) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'joinUser'");
    }

    @Override
    public LoginResponse doSocialLogin(SocialLoginRequest request) {
        throw new UnsupportedOperationException("Unimplemented method 'doSocialLogin'");
    }
}
