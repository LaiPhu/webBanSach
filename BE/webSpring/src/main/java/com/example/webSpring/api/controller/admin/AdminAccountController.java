package com.example.webSpring.api.controller.admin;

import com.example.webSpring.api.core.Constants;
import com.example.webSpring.api.service.user.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.Api.Path.PREFIX /*+ ConstUrl.URL_ACCOUNT*/)
@Api(tags = Constants.Api.Tag.ACCOUNT_MANAGEMENT)
public class AdminAccountController {

    private final UserService userService;

//    @ApiOperation(value = "create User", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.ACCOUNT_MANAGEMENT)
//    @ApiResponses(
//            value = {
//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "New User create success",
//                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
//                    ),
//            }
//    )
//    @SecureApi
//    @PostMapping(value = ConstUrl.URL_CREATE, consumes = { MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" })
//    public ApiResponseEntity createUser(@RequestBody @Valid AccountVM accountVM) {
//        userService.createUser(accountVM);
//        return ApiResponseEntity.bodyOk().build();
//    }
}
