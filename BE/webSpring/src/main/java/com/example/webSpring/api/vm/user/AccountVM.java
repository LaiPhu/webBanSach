package com.example.webSpring.api.vm.user;

import com.example.webSpring.api.core.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AccountVM {

    @NotBlank
    @Schema(description = "pass word of user", example = Constants.Api.FieldExample.PASS_WORD)
    @Size(min = 6, max = 32, message = Constants.ValidationMessage.INVALID_SIZE_VALUE)
    @Pattern(regexp = "^[\\w\\d$&+,:;=?@#|'<>.^*()%!-]*", message = Constants.ValidationMessage.INVALID_PASSWORD)
    private String passWord;

    @NotBlank
    @Schema(description = "Phone number of user", example = Constants.Api.FieldExample.PHONE_NUMBER, required = true)
    @Size(max = 50, message = Constants.ValidationMessage.INVALID_SIZE_VALUE)
    private String phoneNumber;
}
