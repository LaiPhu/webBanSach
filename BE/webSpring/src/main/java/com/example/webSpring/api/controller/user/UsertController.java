package com.example.webSpring.api.controller.user;

import com.example.webSpring.api.commons.ConstUrl;
import com.example.webSpring.api.core.ApiResponseEntity;
import com.example.webSpring.api.core.Constants;
import com.example.webSpring.api.security.Api.SecureApi;
import com.example.webSpring.api.service.user.UserService;
import com.example.webSpring.api.vm.user.LoginVM;
import com.example.webSpring.api.vm.user.UserVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.Api.Path.PREFIX + ConstUrl.URL_ACCOUNT)
@Api(tags = Constants.Api.Tag.ACCOUNT_MANAGEMENT)
public class UsertController {

    private final UserService userService;

    @ApiOperation(value = "create User", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.ACCOUNT_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "New User create success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @PostMapping(value = ConstUrl.URL_CREATE, consumes = { MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" })
    public ApiResponseEntity createUser(@RequestBody @Valid UserVM accountVM) {
        userService.createUser(accountVM);
        return ApiResponseEntity.bodyOk().build();
    }

    @ApiOperation(value = "User login", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.ACCOUNT_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User login success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @PostMapping(value = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" })
    public ApiResponseEntity authenticateUser(@Valid @RequestBody LoginVM loginVM) {
        return ApiResponseEntity.bodyOk().build();
    }
}
