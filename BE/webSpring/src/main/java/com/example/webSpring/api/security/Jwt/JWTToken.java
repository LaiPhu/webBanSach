
package com.example.webSpring.api.security.Jwt;

import com.example.webSpring.api.dto.admin.AdminUserDTO;
import com.example.webSpring.api.dto.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JWTToken {

    @JsonProperty("access_token")
    private String accessToken;

    private UserDTO user;

    private AdminUserDTO adminUser;

    private boolean isFirstLogin;
    private boolean checkTimeResetPassword;

    private String changePasswordCode;

    private String lockTime;
}
