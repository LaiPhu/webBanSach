package com.example.webSpring.api.service.admin;

import com.example.webSpring.api.core.exception.BusinessException;
import com.example.webSpring.api.core.exception.ExceptionProblem;
import com.example.webSpring.api.core.exception.ExceptionType;
import com.example.webSpring.api.dto.admin.AdminBookDTO;
import com.example.webSpring.api.model.admin.Account;
import com.example.webSpring.api.repository.admin.AccountRepository;
import com.example.webSpring.api.repository.admin.AdminBookRepository;
import com.example.webSpring.api.security.SecurityUtils;
import com.example.webSpring.api.vm.admin.AdminBookVM;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminBookService {

    private final AdminBookRepository adminBookRepository;

    private final AccountRepository accountRepository;

    public AdminBookDTO createBook(AdminBookVM adminBookVM){
        String userId = SecurityUtils
                .getCurrentLoggedInUserId()
                .orElseThrow(
                        () -> new ExceptionProblem(ExceptionType.NOT_FOUND_USER, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.UNAUTHORIZED)
                );
        Account account = accountRepository.findById(userId).orElse(null);
        if (account == null) {
            throw new BusinessException(ExceptionType.NOT_FOUND_USER.getMessageKey());
        }
        
        return null;
    }
}
