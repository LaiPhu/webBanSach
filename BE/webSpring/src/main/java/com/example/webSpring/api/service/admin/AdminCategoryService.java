package com.example.webSpring.api.service.admin;

import com.example.webSpring.api.core.exception.BusinessException;
import com.example.webSpring.api.core.exception.ExceptionProblem;
import com.example.webSpring.api.core.exception.ExceptionType;
import com.example.webSpring.api.dto.admin.AdminBookDTO;
import com.example.webSpring.api.dto.admin.AdminCategoryDTO;
import com.example.webSpring.api.mapper.admin.AdminBookMapper;
import com.example.webSpring.api.model.admin.*;
import com.example.webSpring.api.repository.admin.*;
import com.example.webSpring.api.security.SecurityUtils;
import com.example.webSpring.api.vm.admin.AdminAddBookVM;
import com.example.webSpring.api.vm.admin.AdminBookVM;
import com.example.webSpring.api.vm.admin.AdminCategoryVM;
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
public class AdminCategoryService {

    private final AccountRepository accountRepository;

    private final AdminCategoryRepository adminCategoryRepository;

    public List<AdminCategoryDTO> getAllCategory(){
        String userId = SecurityUtils
                .getCurrentLoggedInUserId()
                .orElseThrow(
                        () -> new ExceptionProblem(ExceptionType.NOT_FOUND_USER, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.UNAUTHORIZED)
                );
        Account account = accountRepository.findById(userId).orElse(null);
        if (account == null) {
            throw new BusinessException(ExceptionType.NOT_FOUND_USER.getMessageKey());
        }
        List<AdminCategory> categories = adminCategoryRepository.findAll();
        List<AdminCategoryDTO> list = new ArrayList<>();
        if(!CollectionUtils.isEmpty(categories)){
            categories.forEach(
                    category -> {
                        AdminCategoryDTO adminCategoryDTO = new AdminCategoryDTO();

                        adminCategoryDTO.setName(category.getName());
                        adminCategoryDTO.setId(category.getId());
                        list.add(adminCategoryDTO);
                    }
            );
        }
        return list;
    }

    public AdminCategoryDTO createCategory(String name){
        String userId = SecurityUtils
                .getCurrentLoggedInUserId()
                .orElseThrow(
                        () -> new ExceptionProblem(ExceptionType.NOT_FOUND_USER, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.UNAUTHORIZED)
                );
        Account account = accountRepository.findById(userId).orElse(null);
        if (account == null) {
            throw new BusinessException(ExceptionType.NOT_FOUND_USER.getMessageKey());
        }
        AdminCategory category = new AdminCategory();
        category.setName(name);
        category.setDeleted(false);
        adminCategoryRepository.save(category);
        AdminCategoryDTO adminCategoryDTO = new AdminCategoryDTO();
        adminCategoryDTO.setName(category.getName());
        adminCategoryDTO.setId(category.getId());
        return adminCategoryDTO;
    }

    public List<AdminCategoryDTO> searchCategory(String search){
        String userId = SecurityUtils
                .getCurrentLoggedInUserId()
                .orElseThrow(
                        () -> new ExceptionProblem(ExceptionType.NOT_FOUND_USER, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.UNAUTHORIZED)
                );
        Account account = accountRepository.findById(userId).orElse(null);
        if (account == null) {
            throw new BusinessException(ExceptionType.NOT_FOUND_USER.getMessageKey());
        }
        List<AdminCategory> categories = adminCategoryRepository.findAllByNameIgnoreCaseOrderByCreatedDateDesc(search);
        List<AdminCategoryDTO> categoryDTOS = new ArrayList<>();
        if(!CollectionUtils.isEmpty(categories)){
            categories.forEach(
                    category -> {
                        AdminCategoryDTO adminCategoryDTO = new AdminCategoryDTO();

                        adminCategoryDTO.setName(category.getName());
                        adminCategoryDTO.setId(category.getId());
                        categoryDTOS.add(adminCategoryDTO);
                    }
            );
        }
        return categoryDTOS;
    }

    public AdminCategoryDTO updateCategory(AdminCategoryVM adminCategoryVM){
        String userId = SecurityUtils
                .getCurrentLoggedInUserId()
                .orElseThrow(
                        () -> new ExceptionProblem(ExceptionType.NOT_FOUND_USER, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.UNAUTHORIZED)
                );
        Account account = accountRepository.findById(userId).orElse(null);
        if (account == null) {
            throw new BusinessException(ExceptionType.NOT_FOUND_USER.getMessageKey());
        }
        AdminCategory category = adminCategoryRepository.findById(adminCategoryVM.getId())
                .orElseThrow(() -> new BusinessException(ExceptionType.NOT_FOUND_CATEGORY.getMessageKey()));
        if(adminCategoryVM.getName() != null){
            category.setName(adminCategoryVM.getName());
        }
        adminCategoryRepository.save(category);

        AdminCategoryDTO adminCategoryDTO = new AdminCategoryDTO();

        adminCategoryDTO.setName(category.getName());
        adminCategoryDTO.setId(category.getId());
        return adminCategoryDTO;
    }

    public void deleteCategory(String categoryId){
        String userId = SecurityUtils
                .getCurrentLoggedInUserId()
                .orElseThrow(
                        () -> new ExceptionProblem(ExceptionType.NOT_FOUND_USER, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.UNAUTHORIZED)
                );
        Account account = accountRepository.findById(userId).orElse(null);
        if (account == null) {
            throw new BusinessException(ExceptionType.NOT_FOUND_USER.getMessageKey());
        }
        AdminCategory category = adminCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new BusinessException(ExceptionType.NOT_FOUND_CATEGORY.getMessageKey()));
        category.setDeleted(true);
        adminCategoryRepository.save(category);
    }
}
