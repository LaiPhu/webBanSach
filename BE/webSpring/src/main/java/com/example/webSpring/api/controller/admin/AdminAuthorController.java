package com.example.webSpring.api.controller.admin;

import com.example.webSpring.api.commons.ConstUrl;
import com.example.webSpring.api.core.ApiResponseEntity;
import com.example.webSpring.api.core.Constants;
import com.example.webSpring.api.security.Api.SecureApi;
import com.example.webSpring.api.service.admin.AdminAuthorService;
import com.example.webSpring.api.vm.admin.AdminAddAuthorVM;
import com.example.webSpring.api.vm.admin.AdminAuthorVM;
import com.example.webSpring.api.vm.admin.AdminCategoryVM;
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
@RequestMapping(Constants.Api.Path.ADMIN + ConstUrl.URL_AUTHOR)
@Api(tags = Constants.Api.Tag.AUTHOR_MANAGEMENT)
@Validated
public class AdminAuthorController {

    private final AdminAuthorService adminAuthorService;

    @ApiOperation(value = "create Author", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.AUTHOR_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "New Author create success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @PostMapping(value = ConstUrl.URL_CREATE, consumes = { MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" })
    public ApiResponseEntity createAuthor(@RequestBody @Valid AdminAddAuthorVM adminAddAuthorVM) {
        return ApiResponseEntity.bodyOk().bodyData(adminAuthorService.createAuthor(adminAddAuthorVM)).build();
    }

    @ApiOperation(
            value = "Delete Author",
            response = ApiResponseEntity.BodyResponse.class,
            tags = Constants.Api.Tag.AUTHOR_MANAGEMENT
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Delete Author success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @PostMapping(value = ConstUrl.URL_DELETE)
    public ApiResponseEntity deleteAuthor(@RequestParam String AuthorId) {
        adminAuthorService.deleteAuthor(AuthorId);
        return ApiResponseEntity.bodyOk().build();
    }

    @ApiOperation(value = "Get list Author", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.AUTHOR_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Get list Author success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @GetMapping(
            value = ConstUrl.URL_LIST)
    public ApiResponseEntity getAllAuthor() throws Exception {
        return ApiResponseEntity.bodyOk().bodyData(adminAuthorService.getAllAuthor()).build();
    }

    @ApiOperation(value = "Search Author by name", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.AUTHOR_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Search Author by name success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @GetMapping(
            value = ConstUrl.URL_SEARCH, consumes = { MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" })
    public ApiResponseEntity searchAuthor(@RequestParam @Valid String search) throws Exception {
        return ApiResponseEntity.bodyOk().bodyData(adminAuthorService.searchAuthor(search)).build();
    }

    @ApiOperation(value = "Update Author", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.AUTHOR_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Update Author success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @PostMapping(value = ConstUrl.URL_UPDATE, consumes = { MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" })
    public ApiResponseEntity updateAuthor(@RequestBody @Valid AdminAuthorVM adminAuthorVM) {
        return ApiResponseEntity.bodyOk().bodyData(adminAuthorService.updateAuthor(adminAuthorVM)).build();
    }
}
