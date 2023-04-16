package com.example.webSpring.api.vm.admin;

import com.example.webSpring.api.core.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class AdminCategoryVM {

    @Schema(description = "Id of Category", example = Constants.Api.FieldExample.ID, required = true)
    @NotBlank
    private String id;

    @Schema(description = "Name of Category", example = Constants.Api.FieldExample.NAME, required = true)
    @NotBlank
    private String name;
}
