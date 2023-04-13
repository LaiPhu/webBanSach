package com.example.webSpring.api.controller.admin;

import com.example.webSpring.api.commons.ConstUrl;
import com.example.webSpring.api.core.ApiResponseEntity;
import com.example.webSpring.api.core.Constants;
import com.example.webSpring.api.security.Api.SecureApi;
import com.example.webSpring.api.service.admin.AdminBookService;
import com.example.webSpring.api.vm.admin.AdminAddBookVM;
import com.example.webSpring.api.vm.admin.AdminBookVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.Api.Path.ADMIN + ConstUrl.URL_BOOK)
@Api(tags = Constants.Api.Tag.BOOK_MANAGEMENT)
@Validated
public class AdminBookController {

    private final AdminBookService adminBookService;

    @ApiOperation(value = "create book", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.BOOK_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "New book create success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @PostMapping(value = ConstUrl.URL_CREATE, consumes = { MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" })
    public ApiResponseEntity createBook(@RequestBody @Valid AdminAddBookVM adminBookVM) {
        return ApiResponseEntity.bodyOk().bodyData(adminBookService.createBook(adminBookVM)).build();
    }

    @ApiOperation(
            value = "Delete book",
            response = ApiResponseEntity.BodyResponse.class,
            tags = Constants.Api.Tag.BOOK_MANAGEMENT
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Delete book success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @PostMapping(value = ConstUrl.URL_BOOK + ConstUrl.URL_DELETE)
    public ApiResponseEntity deleteBook(@RequestParam String bookId) {
        adminBookService.deleteBook(bookId);
        return ApiResponseEntity.bodyOk().build();
    }

    @ApiOperation(value = "Get list book", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.BOOK_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Get list book success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @GetMapping(
            value = ConstUrl.URL_LIST)
    public ApiResponseEntity getAllBook() throws Exception {
        return ApiResponseEntity.bodyOk().bodyData(adminBookService.getAllBook()).build();
    }

    @ApiOperation(value = "Get book by id", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.BOOK_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Get book by id success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @GetMapping(
            value = ConstUrl.URL_DETAIL)
    public ApiResponseEntity getBookById(@RequestParam @Valid String bookId) throws Exception {
        return ApiResponseEntity.bodyOk().bodyData(adminBookService.getBookById(bookId)).build();
    }

    @ApiOperation(value = "Search book by name", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.BOOK_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Search book by name success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @GetMapping(
            value = ConstUrl.URL_SEARCH, consumes = { MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" })
    public ApiResponseEntity searchBook(@RequestParam @Valid String search) throws Exception {
        return ApiResponseEntity.bodyOk().bodyData(adminBookService.searchBook(search)).build();
    }

    @ApiOperation(value = "Update book", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.BOOK_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Update book success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @PostMapping(value = ConstUrl.URL_UPDATE, consumes = { MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" })
    public ApiResponseEntity updateBook(@RequestBody @Valid AdminBookVM adminBookVM) {
        return ApiResponseEntity.bodyOk().bodyData(adminBookService.updateBook(adminBookVM)).build();
    }
}
