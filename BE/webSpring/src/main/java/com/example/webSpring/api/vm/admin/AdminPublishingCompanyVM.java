package com.example.webSpring.api.vm.admin;

import com.example.webSpring.api.core.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class AdminPublishingCompanyVM {

    @Schema(description = "Id of Publishing Company", example = Constants.Api.FieldExample.ID, required = true)
    @NotBlank
    private String id;

    @Schema(description = "address of Publishing Company", example = Constants.Api.FieldExample.NAME)
    private String address;

    @Schema(description = "Name of Publishing Company", example = Constants.Api.FieldExample.NAME)
    private String name;

    @Schema(description = "phone Number of Publishing Company", example = Constants.Api.FieldExample.PHONE_NUMBER)
    private Integer phoneNumber;
}
