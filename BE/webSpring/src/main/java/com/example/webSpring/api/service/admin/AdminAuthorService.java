package com.example.webSpring.api.service.admin;

import com.example.webSpring.api.core.exception.BusinessException;
import com.example.webSpring.api.core.exception.ExceptionProblem;
import com.example.webSpring.api.core.exception.ExceptionType;
import com.example.webSpring.api.dto.admin.AdminAuthorDTO;
import com.example.webSpring.api.mapper.admin.AdminAuthorMapper;
import com.example.webSpring.api.model.admin.Account;
import com.example.webSpring.api.model.admin.AdminAuthor;
import com.example.webSpring.api.repository.admin.AccountRepository;
import com.example.webSpring.api.repository.admin.AdminAuthorRepository;
import com.example.webSpring.api.repository.admin.AdminAuthorRepository;
import com.example.webSpring.api.security.SecurityUtils;
import com.example.webSpring.api.vm.admin.AdminAddAuthorVM;
import com.example.webSpring.api.vm.admin.AdminAuthorVM;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.zalando.problem.Status;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminAuthorService {

    private final AccountRepository accountRepository;

    private final AdminAuthorRepository adminAuthorRepository;

    public List<AdminAuthorDTO> getAllAuthor(){
        String userId = SecurityUtils
                .getCurrentLoggedInUserId()
                .orElseThrow(
                        () -> new ExceptionProblem(ExceptionType.NOT_FOUND_USER, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.UNAUTHORIZED)
                );
        Account account = accountRepository.findById(userId).orElse(null);
        if (account == null) {
            throw new BusinessException(ExceptionType.NOT_FOUND_USER.getMessageKey());
        }
        List<AdminAuthor> adminAuthors = adminAuthorRepository.findAll();
        List<AdminAuthorDTO> list = new ArrayList<>();
        if(!CollectionUtils.isEmpty(adminAuthors)){
            adminAuthors.forEach(
                    author -> {
                        AdminAuthorDTO adminAuthorDTO = AdminAuthorMapper.INSTANCE.toDto(author);
                        list.add(adminAuthorDTO);
                    }
            );
        }
        return list;
    }

    public AdminAuthorDTO createAuthor(AdminAddAuthorVM adminAddAuthorVM){
        String userId = SecurityUtils
                .getCurrentLoggedInUserId()
                .orElseThrow(
                        () -> new ExceptionProblem(ExceptionType.NOT_FOUND_USER, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.UNAUTHORIZED)
                );
        Account account = accountRepository.findById(userId).orElse(null);
        if (account == null) {
            throw new BusinessException(ExceptionType.NOT_FOUND_USER.getMessageKey());
        }
        AdminAuthor author = AdminAuthorMapper.INSTANCE.toAddAuthor(adminAddAuthorVM);
        adminAuthorRepository.save(author);
        AdminAuthorDTO adminAuthorDTO = AdminAuthorMapper.INSTANCE.toDto(author);
        return adminAuthorDTO;
    }

    public List<AdminAuthorDTO> searchAuthor(String search){
        String userId = SecurityUtils
                .getCurrentLoggedInUserId()
                .orElseThrow(
                        () -> new ExceptionProblem(ExceptionType.NOT_FOUND_USER, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.UNAUTHORIZED)
                );
        Account account = accountRepository.findById(userId).orElse(null);
        if (account == null) {
            throw new BusinessException(ExceptionType.NOT_FOUND_USER.getMessageKey());
        }
        List<AdminAuthor> authors = adminAuthorRepository.findAllByNameIgnoreCaseOrderByCreatedDateDesc(search);
        List<AdminAuthorDTO> authorDTOS = new ArrayList<>();
        if(!CollectionUtils.isEmpty(authors)){
            authors.forEach(
                    author -> {
                        AdminAuthorDTO adminAuthorDTO = AdminAuthorMapper.INSTANCE.toDto(author);
                        authorDTOS.add(adminAuthorDTO);
                    }
            );
        }
        return authorDTOS;
    }

    public AdminAuthorDTO updateAuthor(AdminAuthorVM adminAuthorVM){
        String userId = SecurityUtils
                .getCurrentLoggedInUserId()
                .orElseThrow(
                        () -> new ExceptionProblem(ExceptionType.NOT_FOUND_USER, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.UNAUTHORIZED)
                );
        Account account = accountRepository.findById(userId).orElse(null);
        if (account == null) {
            throw new BusinessException(ExceptionType.NOT_FOUND_USER.getMessageKey());
        }
        AdminAuthor author = adminAuthorRepository.findById(adminAuthorVM.getId())
                .orElseThrow(() -> new BusinessException(ExceptionType.NOT_FOUND_AUTHOR.getMessageKey()));
        AdminAuthor updateAuthor = AdminAuthorMapper.INSTANCE.toAuthor(adminAuthorVM);
        adminAuthorRepository.save(updateAuthor);
        AdminAuthorDTO adminAuthorDTO = AdminAuthorMapper.INSTANCE.toDto(updateAuthor);
        return adminAuthorDTO;
    }

    public void deleteAuthor(String AuthorId){
        String userId = SecurityUtils
                .getCurrentLoggedInUserId()
                .orElseThrow(
                        () -> new ExceptionProblem(ExceptionType.NOT_FOUND_USER, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.UNAUTHORIZED)
                );
        Account account = accountRepository.findById(userId).orElse(null);
        if (account == null) {
            throw new BusinessException(ExceptionType.NOT_FOUND_USER.getMessageKey());
        }
        AdminAuthor author = adminAuthorRepository.findById(AuthorId)
                .orElseThrow(() -> new BusinessException(ExceptionType.NOT_FOUND_AUTHOR.getMessageKey()));
        author.setDeleted(true);
        adminAuthorRepository.save(author);
    }
}
