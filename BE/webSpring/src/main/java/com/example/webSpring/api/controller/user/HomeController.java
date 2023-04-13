package com.example.webSpring.api.controller.user;

import com.example.webSpring.api.commons.ConstUrl;
import com.example.webSpring.api.core.ApiResponseEntity;
import com.example.webSpring.api.core.Constants;
import com.example.webSpring.api.security.Api.SecureApi;
import com.example.webSpring.api.service.user.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = Constants.Api.Tag.HOME)
@RequestMapping(Constants.Api.Path.PREFIX + ConstUrl.URL_HOME)
@RequiredArgsConstructor
@RestController
public class HomeController {

    private final HomeService homeService;

    @ApiOperation(value = "Get all category", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.HOME_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Get all category success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @GetMapping(
            value = ConstUrl.URL_LIST + "/category")
    public ApiResponseEntity getCategory() throws Exception {
        return ApiResponseEntity.bodyOk().bodyData(homeService.getListCategory()).build();
    }

    @ApiOperation(value = "add area", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.HOME_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "New area add success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @PostMapping(
            value = ConstUrl.URL_ADD,
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE + ";charset=UTF-8", MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" }
    )
    public ApiResponseEntity addArea() throws Exception {
        return ApiResponseEntity.bodyOk().build();
    }

    @ApiOperation(value = "Get top 10 book selling", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.HOME_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Get top 10 book selling success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @GetMapping(
            value = ConstUrl.URL_LIST + "/top10-book-selling")
    public ApiResponseEntity getListBookSelling() throws Exception {
        return ApiResponseEntity.bodyOk().bodyData(homeService.getListBookSelling()).build();
    }

    @ApiOperation(value = "Get all book update", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.HOME_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Get all book update success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @GetMapping(
            value = ConstUrl.URL_LIST + "/book-update")
    public ApiResponseEntity getListBookUpdate() throws Exception {
        return ApiResponseEntity.bodyOk().bodyData(homeService.getListBookUpdate()).build();
    }

    @ApiOperation(value = "Get top 3 book selling", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.HOME_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Get top 3 book selling success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @GetMapping(
            value = ConstUrl.URL_LIST + "/top3-book-selling")
    public ApiResponseEntity getListBookSelling3() throws Exception {
        return ApiResponseEntity.bodyOk().bodyData(homeService.getListBookSelling3()).build();
    }

}
