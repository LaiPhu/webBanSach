package com.example.webSpring.api.controller.admin;

import com.example.webSpring.api.commons.ConstUrl;
import com.example.webSpring.api.core.ApiResponseEntity;
import com.example.webSpring.api.core.Constants;
import com.example.webSpring.api.security.Api.SecureApi;
import com.example.webSpring.api.service.admin.AdminBookService;
import com.example.webSpring.api.vm.user.BookVM;
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
@RequestMapping(Constants.Api.Path.PREFIX + ConstUrl.URL_BOOK)
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
    public ApiResponseEntity createFacility(@RequestBody @Valid BookVM bookVM) {
        return ApiResponseEntity.bodyOk().bodyData(adminBookService.createBook(bookVM)).build();
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
    public ApiResponseEntity userCancelFacilityMember(@RequestParam String memberId) {
//        bookService.userCancelFacilityMember(memberId);
        return ApiResponseEntity.bodyOk().build();
    }

//    @ApiOperation(
//            value = "get List book by",
//            response = ApiResponseEntity.BodyResponse.class,
//            tags = Constants.Api.Tag.BOOK_MANAGEMENT
//    )
//    @ApiResponses(
//            value = {
//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "get List book by success",
//                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
//                    ),
//            }
//    )
//    @SecureApi
//    @GetMapping(
//            value = ConstUrl.URL_LIST,
//            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE + " ;charset=UTF-8", MediaType.APPLICATION_OCTET_STREAM_VALUE + " ;charset=UTF-8" }
//    )
//    public ApiResponseEntity getListAreaByFacilityId(
//            @RequestParam String facilityId,
//            @RequestParam(required = false, defaultValue = "") String searchName
//    ) {
//        return ApiResponseEntity.bodyOk().bodyData(bookService.createBook(bookVM)).build();
//    }
}
