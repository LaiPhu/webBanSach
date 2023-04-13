package com.example.webSpring.api.vm.user;

import com.example.webSpring.api.core.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginVM {

    @NotBlank
    @Schema(description = "Phone number of user", example = Constants.Api.FieldExample.PHONE_NUMBER, required = true)
    private String phoneNumber;

    @NotBlank
    @Schema(description = "pass word of user", example = Constants.Api.FieldExample.PASS_WORD)
    private String passWord;
}
