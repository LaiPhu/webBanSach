package com.example.webSpring.api.dto.user;

import com.example.webSpring.api.core.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    @Schema(description = "Id of User", example = Constants.Api.FieldExample.ID)
    private String id;

    @Schema(description = "Name of User", example = Constants.Api.FieldExample.NAME)
    private String address;

    @Schema(description = "phoneNumber of User", example = Constants.Api.FieldExample.PHONE_NUMBER)
    private String phoneNumber;

    @Schema(description = "Name of User", example = Constants.Api.FieldExample.NAME)
    private String name;

    @Schema(description = "date of User", example = Constants.Api.FieldExample.DATE)
    private Long date;

    @Schema(description = "passWord of User", example = Constants.Api.FieldExample.PASS_WORD)
    private String passWord;

    @Schema(description = "status of User", example = Constants.Api.FieldExample.STATUS)
    private Integer status;

    @Schema(description = "permission of User", example = Constants.Api.FieldExample.STATUS)
    private Integer permission;
}
