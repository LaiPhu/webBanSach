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
@RequestMapping(Constants.Api.Path.ADMIN /*+ ConstUrl.URL_ACCOUNT*/)
@Api(tags = Constants.Api.Tag.ACCOUNT_MANAGEMENT)
public class AdminAccountController {
}
