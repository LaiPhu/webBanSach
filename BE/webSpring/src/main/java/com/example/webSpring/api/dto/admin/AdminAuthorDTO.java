package com.example.webSpring.api.dto.admin;

import com.example.webSpring.api.core.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class AdminAuthorDTO {

    @Schema(description = "Id of author", example = Constants.Api.FieldExample.ID)
    private String id;

    @Schema(description = "home town of author", example = Constants.Api.FieldExample.NAME)
    private String hometown;

    @Schema(description = "date of author", example = Constants.Api.FieldExample.DATE)
    private Long date;

    @Schema(description = "Name of author", example = Constants.Api.FieldExample.NAME)
    private String name;

    @Schema(description = "story of author", example = Constants.Api.FieldExample.NAME)
    private String story;
}
