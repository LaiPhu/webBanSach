package com.example.webSpring.api.vm.admin;

import com.example.webSpring.api.core.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminAddAuthorVM {
    @Schema(description = "home town of author", example = Constants.Api.FieldExample.NAME)
    private String hometown;

    @Schema(description = "date of author", example = Constants.Api.FieldExample.DATE)
    private Long date;

    @Schema(description = "Name of author", example = Constants.Api.FieldExample.NAME)
    private String name;

    @Schema(description = "story of author", example = Constants.Api.FieldExample.NAME)
    private String story;
}
