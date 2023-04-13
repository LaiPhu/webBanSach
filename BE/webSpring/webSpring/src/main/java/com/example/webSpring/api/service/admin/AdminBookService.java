package com.example.webSpring.api.service.admin;

import com.example.webSpring.api.core.exception.BusinessException;
import com.example.webSpring.api.core.exception.ExceptionProblem;
import com.example.webSpring.api.core.exception.ExceptionType;
import com.example.webSpring.api.dto.admin.AdminBookDTO;
import com.example.webSpring.api.mapper.admin.AdminBookMapper;
import com.example.webSpring.api.model.admin.*;
import com.example.webSpring.api.repository.admin.*;
import com.example.webSpring.api.security.SecurityUtils;
import com.example.webSpring.api.vm.admin.AdminAddBookVM;
import com.example.webSpring.api.vm.admin.AdminBookVM;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.zalando.problem.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminBookService {

    private final AdminBookRepository adminBookRepository;

    private final AccountRepository accountRepository;

    private final AdminAuthorRepository adminAuthorRepository;

    private final AdminCategoryRepository adminCategoryRepository;

    private final AdminPublishingCompanyRepository adminPublishingCompanyRepository;

    public List<AdminBookDTO> getAllBook(){
        String userId = SecurityUtils
                .getCurrentLoggedInUserId()
                .orElseThrow(
                        () -> new ExceptionProblem(ExceptionType.NOT_FOUND_USER, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.UNAUTHORIZED)
                );
        Account account = accountRepository.findById(userId).orElse(null);
        if (account == null) {
            throw new BusinessException(ExceptionType.NOT_FOUND_USER.getMessageKey());
        }
        List<AdminBook> books = adminBookRepository.findAll();
        List<AdminBookDTO> bookDTOS = new ArrayList<>();
        if(!CollectionUtils.isEmpty(books)){
            books.forEach(
                    book -> {
                        AdminBookDTO bookDTO = AdminBookMapper.INSTANCE.toDto(book);
                        Optional<AdminCategory> adminCategory = adminCategoryRepository.findById(book.getCategoryId());
                        if(adminCategory.isPresent()){
                            bookDTO.setCategoryName(adminCategory.get().getName());
                        }
                        Optional<AdminPublishingCompany> adminPublishingCompany = adminPublishingCompanyRepository.findById(book.getPublishingCompanyId());
                        if(adminPublishingCompany.isPresent()){
                            bookDTO.setPublishingCompanyName(adminPublishingCompany.get().getName());
                        }
                        Optional<AdminAuthor> adminAuthor = adminAuthorRepository.findById(book.getAuthorId());
                        if(adminAuthor.isPresent()){
                            bookDTO.setAuthorName(adminAuthor.get().getName());
                        }

                        bookDTOS.add(bookDTO);
                    }
            );
        }
        return bookDTOS;
    }

    public AdminBookDTO getBookById(String bookId){
        String userId = SecurityUtils
                .getCurrentLoggedInUserId()
                .orElseThrow(
                        () -> new ExceptionProblem(ExceptionType.NOT_FOUND_USER, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.UNAUTHORIZED)
                );
        Account account = accountRepository.findById(userId).orElse(null);
        if (account == null) {
            throw new BusinessException(ExceptionType.NOT_FOUND_USER.getMessageKey());
        }
        AdminBook book = adminBookRepository.findById(bookId)
                .orElseThrow(() -> new BusinessException(ExceptionType.NOT_FOUND_BOOK.getMessageKey()));
        AdminBookDTO bookDTO = AdminBookMapper.INSTANCE.toDto(book);

        Optional<AdminCategory> adminCategory = adminCategoryRepository.findById(book.getCategoryId());
        if(adminCategory.isPresent()){
            bookDTO.setCategoryName(adminCategory.get().getName());
        }
        Optional<AdminPublishingCompany> adminPublishingCompany = adminPublishingCompanyRepository.findById(book.getPublishingCompanyId());
        if(adminPublishingCompany.isPresent()){
            bookDTO.setPublishingCompanyName(adminPublishingCompany.get().getName());
        }
        Optional<AdminAuthor> adminAuthor = adminAuthorRepository.findById(book.getAuthorId());
        if(adminAuthor.isPresent()){
            bookDTO.setAuthorName(adminAuthor.get().getName());
        }
        return bookDTO;
    }

    public AdminBookDTO createBook(AdminAddBookVM adminBookVM){
        String userId = SecurityUtils
                .getCurrentLoggedInUserId()
                .orElseThrow(
                        () -> new ExceptionProblem(ExceptionType.NOT_FOUND_USER, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.UNAUTHORIZED)
                );
        Account account = accountRepository.findById(userId).orElse(null);
        if (account == null) {
            throw new BusinessException(ExceptionType.NOT_FOUND_USER.getMessageKey());
        }
        AdminBook book = AdminBookMapper.INSTANCE.toAddBook(adminBookVM);
        book.setDeleted(false);
        AdminBookDTO bookDTO = AdminBookMapper.INSTANCE.toDto(book);
        adminBookRepository.save(book);
        return bookDTO;
    }

    public List<AdminBookDTO> searchBook(String search){
        String userId = SecurityUtils
                .getCurrentLoggedInUserId()
                .orElseThrow(
                        () -> new ExceptionProblem(ExceptionType.NOT_FOUND_USER, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.UNAUTHORIZED)
                );
        Account account = accountRepository.findById(userId).orElse(null);
        if (account == null) {
            throw new BusinessException(ExceptionType.NOT_FOUND_USER.getMessageKey());
        }
        List<AdminBook> books = adminBookRepository.findAllByNameIgnoreCaseOrderByCreatedDateDesc(search);
        List<AdminBookDTO> bookDTOS = new ArrayList<>();
        if(!CollectionUtils.isEmpty(books)){
            books.forEach(
                    book -> {
                        AdminBookDTO bookDTO = AdminBookMapper.INSTANCE.toDto(book);
                        bookDTOS.add(bookDTO);
                    }
            );
        }
        return bookDTOS;
    }

    public AdminBookDTO updateBook(AdminBookVM adminBookVM){
        String userId = SecurityUtils
                .getCurrentLoggedInUserId()
                .orElseThrow(
                        () -> new ExceptionProblem(ExceptionType.NOT_FOUND_USER, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.UNAUTHORIZED)
                );
        Account account = accountRepository.findById(userId).orElse(null);
        if (account == null) {
            throw new BusinessException(ExceptionType.NOT_FOUND_USER.getMessageKey());
        }
        AdminBook book = adminBookRepository.findById(adminBookVM.getId())
                .orElseThrow(() -> new BusinessException(ExceptionType.NOT_FOUND_BOOK.getMessageKey()));
        AdminBook bookUpdate = AdminBookMapper.INSTANCE.toBook(adminBookVM);
        adminBookRepository.save(bookUpdate);
        AdminBookDTO bookDTO = AdminBookMapper.INSTANCE.toDto(bookUpdate);

        Optional<AdminCategory> adminCategory = adminCategoryRepository.findById(book.getCategoryId());
        if(adminCategory.isPresent()){
            bookDTO.setCategoryName(adminCategory.get().getName());
        }
        Optional<AdminPublishingCompany> adminPublishingCompany = adminPublishingCompanyRepository.findById(book.getPublishingCompanyId());
        if(adminPublishingCompany.isPresent()){
            bookDTO.setPublishingCompanyName(adminPublishingCompany.get().getName());
        }
        Optional<AdminAuthor> adminAuthor = adminAuthorRepository.findById(book.getAuthorId());
        if(adminAuthor.isPresent()){
            bookDTO.setAuthorName(adminAuthor.get().getName());
        }
        return bookDTO;
    }

    public void deleteBook(String bookId){
        String userId = SecurityUtils
                .getCurrentLoggedInUserId()
                .orElseThrow(
                        () -> new ExceptionProblem(ExceptionType.NOT_FOUND_USER, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.UNAUTHORIZED)
                );
        Account account = accountRepository.findById(userId).orElse(null);
        if (account == null) {
            throw new BusinessException(ExceptionType.NOT_FOUND_USER.getMessageKey());
        }
        AdminBook book = adminBookRepository.findById(bookId)
                .orElseThrow(() -> new BusinessException(ExceptionType.NOT_FOUND_BOOK.getMessageKey()));
        book.setDeleted(true);
        adminBookRepository.save(book);
    }
}
