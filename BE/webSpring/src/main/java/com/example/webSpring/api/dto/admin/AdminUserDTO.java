package com.example.webSpring.api.dto.admin;

import com.example.webSpring.api.core.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUserDTO {

    @Schema(description = "Id of Admin User", example = Constants.Api.FieldExample.ID)
    private String id;

    @Schema(description = "Name of Admin User", example = Constants.Api.FieldExample.NAME)
    private String address;

    @Schema(description = "phoneNumber of Admin User", example = Constants.Api.FieldExample.PHONE_NUMBER)
    private String phoneNumber;

    @Schema(description = "Name of Admin User", example = Constants.Api.FieldExample.NAME)
    private String name;

    @Schema(description = "date of Admin User", example = Constants.Api.FieldExample.DATE)
    private Long date;

    @Schema(description = "passWord of Admin User", example = Constants.Api.FieldExample.PASS_WORD)
    private String passWord;

    @Schema(description = "status of Admin User", example = Constants.Api.FieldExample.STATUS)
    private Integer status;

    @Schema(description = "permission of Admin User", example = Constants.Api.FieldExample.STATUS)
    private Integer permission;
}
