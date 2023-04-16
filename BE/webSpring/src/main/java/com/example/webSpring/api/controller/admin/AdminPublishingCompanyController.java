package com.example.webSpring.api.controller.admin;

import com.example.webSpring.api.commons.ConstUrl;
import com.example.webSpring.api.core.ApiResponseEntity;
import com.example.webSpring.api.core.Constants;
import com.example.webSpring.api.security.Api.SecureApi;
import com.example.webSpring.api.service.admin.AdminPublishingCompanyService;
import com.example.webSpring.api.vm.admin.AdminAddPublishingCompanyVM;
import com.example.webSpring.api.vm.admin.AdminPublishingCompanyVM;
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
@RequestMapping(Constants.Api.Path.ADMIN + ConstUrl.URL_PUBLISHING_COMPANY)
@Api(tags = Constants.Api.Tag.PUBLISHING_COMPANY_MANAGEMENT)
@Validated
public class AdminPublishingCompanyController {

    private final AdminPublishingCompanyService adminPublishingCompanyService;

    @ApiOperation(value = "create Publishing Company", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.PUBLISHING_COMPANY_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "New Publishing Company create success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @PostMapping(value = ConstUrl.URL_CREATE, consumes = { MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" })
    public ApiResponseEntity createPublishingCompany(@RequestBody @Valid AdminAddPublishingCompanyVM adminAddPublishingCompanyVM) {
        return ApiResponseEntity.bodyOk().bodyData(adminPublishingCompanyService.createPublishingCompany(adminAddPublishingCompanyVM)).build();
    }

    @ApiOperation(
            value = "Delete PublishingCompany",
            response = ApiResponseEntity.BodyResponse.class,
            tags = Constants.Api.Tag.PUBLISHING_COMPANY_MANAGEMENT
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Delete PublishingCompany success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @PostMapping(value = ConstUrl.URL_DELETE)
    public ApiResponseEntity deletePublishingCompany(@RequestParam String PublishingCompanyId) {
        adminPublishingCompanyService.deletePublishingCompany(PublishingCompanyId);
        return ApiResponseEntity.bodyOk().build();
    }

    @ApiOperation(value = "Get list PublishingCompany", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.PUBLISHING_COMPANY_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Get list PublishingCompany success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @GetMapping(
            value = ConstUrl.URL_LIST)
    public ApiResponseEntity getAllPublishingCompany() throws Exception {
        return ApiResponseEntity.bodyOk().bodyData(adminPublishingCompanyService.getAllPublishingCompany()).build();
    }

    @ApiOperation(value = "Search PublishingCompany by name", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.PUBLISHING_COMPANY_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Search PublishingCompany by name success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @GetMapping(
            value = ConstUrl.URL_SEARCH, consumes = { MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" })
    public ApiResponseEntity searchPublishingCompany(@RequestParam @Valid String search) throws Exception {
        return ApiResponseEntity.bodyOk().bodyData(adminPublishingCompanyService.searchPublishingCompany(search)).build();
    }

    @ApiOperation(value = "Update PublishingCompany", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.PUBLISHING_COMPANY_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Update PublishingCompany success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @PostMapping(value = ConstUrl.URL_UPDATE, consumes = { MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" })
    public ApiResponseEntity updatePublishingCompany(@RequestBody @Valid AdminPublishingCompanyVM adminPublishingCompanyVM) {
        return ApiResponseEntity.bodyOk().bodyData(adminPublishingCompanyService.updatePublishingCompany(adminPublishingCompanyVM)).build();
    }
}
