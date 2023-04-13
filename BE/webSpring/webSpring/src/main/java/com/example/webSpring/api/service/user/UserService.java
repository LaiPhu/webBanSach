package com.example.webSpring.api.service.user;


import com.example.webSpring.api.core.exception.BusinessException;
import com.example.webSpring.api.core.exception.ExceptionType;
import com.example.webSpring.api.enums.UserPermission;
import com.example.webSpring.api.model.user.User;
import com.example.webSpring.api.repository.user.UserRepository;
import com.example.webSpring.api.security.Jwt.TokenProvider;
import com.example.webSpring.api.security.Utils.Helper;
import com.example.webSpring.api.vm.user.AccountVM;
import com.example.webSpring.api.vm.user.LoginVM;
import com.example.webSpring.api.vm.user.UserVM;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    public void createUser(UserVM accountVM){

        accountVM.setPhoneNumber(Helper.formatPhone(accountVM.getPhoneNumber()));

        User user = userRepository.findDistinctFirstByPhoneNumber(accountVM.getPhoneNumber());

        if (user != null) {
            throw new BusinessException(ExceptionType.EXISTS_USER);
        }
        User user1 = new User();
        user1.setPhoneNumber(accountVM.getPhoneNumber());
        user1.setPassWord(accountVM.getPassWord());
        user1.setPermission(UserPermission.USER.getCode());
        user1.setName(accountVM.getName());
        user1.setDate(accountVM.getDate());
        user1.setAddress(accountVM.getAddress());
        user1.setDeleted(false);
        userRepository.save(user1);

    }

    public String loginUser(LoginVM loginVM){
//        User user = userRepository.findDistinctFirstByPhoneNumber(loginVM.getPhoneNumber());
//        if (user == null) {
//            throw new BusinessException(ExceptionType.NOT_FOUND_USER);
//        }
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginVM.getPhoneNumber(), loginVM.getPassWord()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String jwt = tokenProvider.generateToken(authentication);
//        return jwt;
        return null;
    }

}
