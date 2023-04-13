package com.example.webSpring.api.controller.user;

import com.example.webSpring.api.commons.ConstUrl;
import com.example.webSpring.api.core.ApiResponseEntity;
import com.example.webSpring.api.core.Constants;
import com.example.webSpring.api.security.Api.SecureApi;
import com.example.webSpring.api.service.user.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@Api(tags = Constants.Api.Tag.BOOK)
@RequestMapping(Constants.Api.Path.PREFIX + ConstUrl.URL_BOOK)
@RequiredArgsConstructor
@RestController
public class BookController {
    private final BookService bookService;

    @ApiOperation(value = "Get all book by category id", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.BOOK_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Get all book by category id success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @GetMapping(
            value = ConstUrl.URL_LIST + "/book-by-category")
    public ApiResponseEntity getListBookByCategoryId(@RequestParam @Valid String categoryId) throws Exception {
        return ApiResponseEntity.bodyOk().bodyData(bookService.getListBookByCategoryId(categoryId)).build();
    }
}
