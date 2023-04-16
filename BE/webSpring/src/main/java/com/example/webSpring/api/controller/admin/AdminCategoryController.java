package com.example.webSpring.api.controller.admin;

import com.example.webSpring.api.commons.ConstUrl;
import com.example.webSpring.api.core.ApiResponseEntity;
import com.example.webSpring.api.core.Constants;
import com.example.webSpring.api.security.Api.SecureApi;
import com.example.webSpring.api.service.admin.AdminCategoryService;
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
@RequestMapping(Constants.Api.Path.ADMIN + ConstUrl.URL_CATEGORY)
@Api(tags = Constants.Api.Tag.CATEGORY_MANAGEMENT)
@Validated
public class AdminCategoryController {

    private final AdminCategoryService adminCategoryService;

    @ApiOperation(value = "create category", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.CATEGORY_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "New category create success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @PostMapping(value = ConstUrl.URL_CREATE, consumes = { MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" })
    public ApiResponseEntity createCategory(@RequestParam @Valid String nameCategory) {
        return ApiResponseEntity.bodyOk().bodyData(adminCategoryService.createCategory(nameCategory)).build();
    }

    @ApiOperation(
            value = "Delete Category",
            response = ApiResponseEntity.BodyResponse.class,
            tags = Constants.Api.Tag.CATEGORY_MANAGEMENT
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Delete Category success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @PostMapping(value = ConstUrl.URL_DELETE)
    public ApiResponseEntity deleteCategory(@RequestParam String categoryId) {
        adminCategoryService.deleteCategory(categoryId);
        return ApiResponseEntity.bodyOk().build();
    }

    @ApiOperation(value = "Get list Category", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.CATEGORY_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Get list Category success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @GetMapping(
            value = ConstUrl.URL_LIST)
    public ApiResponseEntity getAllCategory() throws Exception {
        return ApiResponseEntity.bodyOk().bodyData(adminCategoryService.getAllCategory()).build();
    }

    @ApiOperation(value = "Search Category by name", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.CATEGORY_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Search Category by name success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @GetMapping(
            value = ConstUrl.URL_SEARCH, consumes = { MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" })
    public ApiResponseEntity searchCategory(@RequestParam @Valid String search) throws Exception {
        return ApiResponseEntity.bodyOk().bodyData(adminCategoryService.searchCategory(search)).build();
    }

    @ApiOperation(value = "Update Category", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.CATEGORY_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Update Category success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @PostMapping(value = ConstUrl.URL_UPDATE, consumes = { MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" })
    public ApiResponseEntity updateCategory(@RequestBody @Valid AdminCategoryVM adminCategoryVM) {
        return ApiResponseEntity.bodyOk().bodyData(adminCategoryService.updateCategory(adminCategoryVM)).build();
    }
}
