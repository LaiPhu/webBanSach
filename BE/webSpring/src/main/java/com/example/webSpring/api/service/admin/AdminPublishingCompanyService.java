package com.example.webSpring.api.service.admin;

import com.example.webSpring.api.core.exception.BusinessException;
import com.example.webSpring.api.core.exception.ExceptionProblem;
import com.example.webSpring.api.core.exception.ExceptionType;
import com.example.webSpring.api.dto.admin.AdminPublishingCompanyDTO;
import com.example.webSpring.api.mapper.admin.AdminPublishingCompanyMapper;
import com.example.webSpring.api.model.admin.Account;
import com.example.webSpring.api.model.admin.AdminPublishingCompany;
import com.example.webSpring.api.repository.admin.AccountRepository;
import com.example.webSpring.api.repository.admin.AdminPublishingCompanyRepository;
import com.example.webSpring.api.security.SecurityUtils;
import com.example.webSpring.api.vm.admin.AdminAddPublishingCompanyVM;
import com.example.webSpring.api.vm.admin.AdminPublishingCompanyVM;
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
public class AdminPublishingCompanyService {

    private final AccountRepository accountRepository;

    private final AdminPublishingCompanyRepository adminPublishingCompanyRepository;

    public List<AdminPublishingCompanyDTO> getAllPublishingCompany(){
        String userId = SecurityUtils
                .getCurrentLoggedInUserId()
                .orElseThrow(
                        () -> new ExceptionProblem(ExceptionType.NOT_FOUND_USER, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.UNAUTHORIZED)
                );
        Account account = accountRepository.findById(userId).orElse(null);
        if (account == null) {
            throw new BusinessException(ExceptionType.NOT_FOUND_USER.getMessageKey());
        }
        List<AdminPublishingCompany> adminPublishingCompanies = adminPublishingCompanyRepository.findAll();
        List<AdminPublishingCompanyDTO> list = new ArrayList<>();
        if(!CollectionUtils.isEmpty(adminPublishingCompanies)){
            adminPublishingCompanies.forEach(
                    PublishingCompany -> {
                        AdminPublishingCompanyDTO adminPublishingCompanyDTO = AdminPublishingCompanyMapper.INSTANCE.toDto(PublishingCompany);
                        list.add(adminPublishingCompanyDTO);
                    }
            );
        }
        return list;
    }

    public AdminPublishingCompanyDTO createPublishingCompany(AdminAddPublishingCompanyVM adminAddPublishingCompanyVM){
        String userId = SecurityUtils
                .getCurrentLoggedInUserId()
                .orElseThrow(
                        () -> new ExceptionProblem(ExceptionType.NOT_FOUND_USER, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.UNAUTHORIZED)
                );
        Account account = accountRepository.findById(userId).orElse(null);
        if (account == null) {
            throw new BusinessException(ExceptionType.NOT_FOUND_USER.getMessageKey());
        }
        AdminPublishingCompany publishingCompany = AdminPublishingCompanyMapper.INSTANCE.toAddPublishingCompany(adminAddPublishingCompanyVM);
        adminPublishingCompanyRepository.save(publishingCompany);
        AdminPublishingCompanyDTO adminPublishingCompanyDTO = AdminPublishingCompanyMapper.INSTANCE.toDto(publishingCompany);
        return adminPublishingCompanyDTO;
    }

    public List<AdminPublishingCompanyDTO> searchPublishingCompany(String search){
        String userId = SecurityUtils
                .getCurrentLoggedInUserId()
                .orElseThrow(
                        () -> new ExceptionProblem(ExceptionType.NOT_FOUND_USER, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.UNAUTHORIZED)
                );
        Account account = accountRepository.findById(userId).orElse(null);
        if (account == null) {
            throw new BusinessException(ExceptionType.NOT_FOUND_USER.getMessageKey());
        }
        List<AdminPublishingCompany> publishingCompanies = adminPublishingCompanyRepository.findAllByNameIgnoreCaseOrderByCreatedDateDesc(search);
        List<AdminPublishingCompanyDTO> publishingCompanyDTOS = new ArrayList<>();
        if(!CollectionUtils.isEmpty(publishingCompanies)){
            publishingCompanies.forEach(
                    publishingCompany -> {
                        AdminPublishingCompanyDTO adminPublishingCompanyDTO = AdminPublishingCompanyMapper.INSTANCE.toDto(publishingCompany);
                        publishingCompanyDTOS.add(adminPublishingCompanyDTO);
                    }
            );
        }
        return publishingCompanyDTOS;
    }

    public AdminPublishingCompanyDTO updatePublishingCompany(AdminPublishingCompanyVM adminPublishingCompanyVM){
        String userId = SecurityUtils
                .getCurrentLoggedInUserId()
                .orElseThrow(
                        () -> new ExceptionProblem(ExceptionType.NOT_FOUND_USER, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.UNAUTHORIZED)
                );
        Account account = accountRepository.findById(userId).orElse(null);
        if (account == null) {
            throw new BusinessException(ExceptionType.NOT_FOUND_USER.getMessageKey());
        }
        AdminPublishingCompany publishingCompany = adminPublishingCompanyRepository.findById(adminPublishingCompanyVM.getId())
                .orElseThrow(() -> new BusinessException(ExceptionType.NOT_FOUND_PUBLISHING_COMPANY.getMessageKey()));
        AdminPublishingCompany updatePublishingCompany = AdminPublishingCompanyMapper.INSTANCE.toPublishingCompany(adminPublishingCompanyVM);
        adminPublishingCompanyRepository.save(updatePublishingCompany);
        AdminPublishingCompanyDTO adminPublishingCompanyDTO = AdminPublishingCompanyMapper.INSTANCE.toDto(updatePublishingCompany);
        return adminPublishingCompanyDTO;
    }

    public void deletePublishingCompany(String PublishingCompanyId){
        String userId = SecurityUtils
                .getCurrentLoggedInUserId()
                .orElseThrow(
                        () -> new ExceptionProblem(ExceptionType.NOT_FOUND_USER, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.UNAUTHORIZED)
                );
        Account account = accountRepository.findById(userId).orElse(null);
        if (account == null) {
            throw new BusinessException(ExceptionType.NOT_FOUND_USER.getMessageKey());
        }
        AdminPublishingCompany publishingCompany = adminPublishingCompanyRepository.findById(PublishingCompanyId)
                .orElseThrow(() -> new BusinessException(ExceptionType.NOT_FOUND_PUBLISHING_COMPANY.getMessageKey()));
        publishingCompany.setDeleted(true);
        adminPublishingCompanyRepository.save(publishingCompany);
    }
}
