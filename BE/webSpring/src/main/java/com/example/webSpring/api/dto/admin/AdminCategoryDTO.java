package com.example.webSpring.api.dto.admin;

import com.example.webSpring.api.core.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AdminCategoryDTO {

    @Schema(description = "Id of Category", example = Constants.Api.FieldExample.ID)
    private String id;

    @Schema(description = "Name of Category", example = Constants.Api.FieldExample.NAME)
    private String name;
}
