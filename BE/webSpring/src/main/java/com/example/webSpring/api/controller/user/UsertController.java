package com.example.webSpring.api.controller.user;

import com.example.webSpring.api.commons.ConstUrl;
import com.example.webSpring.api.core.ApiResponseEntity;
import com.example.webSpring.api.core.Constants;
import com.example.webSpring.api.security.Api.SecureApi;
import com.example.webSpring.api.security.Jwt.JWTToken;
import com.example.webSpring.api.security.Jwt.TokenProvider;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.Api.Path.PREFIX + ConstUrl.URL_ACCOUNT)
@Api(tags = Constants.Api.Tag.ACCOUNT_MANAGEMENT)
public class UsertController {

    private final UserService userService;

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

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
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginVM.getPhoneNumber(),
                loginVM.getPassWord()
        );
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        JWTToken jwtToken = new JWTToken();

        // Create access token
//        Map<String, Object> claims = new HashMap<>();
        //        claims.put(TokenProvider.CLAIM_DEVICE_ID, loginVM.getDevice().getId());
        String accessToken = tokenProvider.generateToken(authentication);
        jwtToken.setAccessToken(accessToken);

        // Set user's information
        if (!StringUtils.isBlank(accessToken)) {
            //            ModelMapper modelMapper = new ModelMapper();
            //            User.Device device = modelMapper.map(loginVM.getDevice(), User.Device.class);
            //            jwtToken.setUser(adminUserService.login(adminLoginVM.getUserName()));
            jwtToken.setAdminUser(adminUserService.login(loginVM.getPhoneNumber()));
        }

        // Create refresh token
//        claims.put(TokenProvider.CLAIM_REFRESH_TOKEN, true);
//        String refreshToken = tokenProvider.createToken(authentication, false, claims);
//        jwtToken.setRefreshToken(refreshToken);

        // Create secure transaction
//        TravisRsa travisRsa = cacheService.getTravisRsa();
//        if (travisRsa == null) {
//            travisRsa = new TravisRsa();
//            cacheService.setTravisRsa(travisRsa);
//        }
//        jwtToken.setPublicKey(TravisRsa.getBase64PublicKey(travisRsa.getPublicKey()));

        return ApiResponseEntity.bodyOk().bodyData(jwtToken).build();
    }
}
