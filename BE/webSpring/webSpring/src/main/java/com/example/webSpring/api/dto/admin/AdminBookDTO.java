package com.example.webSpring.api.dto.admin;

import com.example.webSpring.api.core.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminBookDTO {

    @Schema(description = "Id of book", example = Constants.Api.FieldExample.ID)
    private String id;

    @Schema(description = "Name of author", example = Constants.Api.FieldExample.NAME)
    private String authorName;

    @Schema(description = "Name of category", example = Constants.Api.FieldExample.NAME)
    private String categoryName;

    @Schema(description = "Name of publishing company", example = Constants.Api.FieldExample.NAME)
    private String publishingCompanyName;

    @Schema(description = "Name of book", example = Constants.Api.FieldExample.NAME)
    private String name;

    @Schema(description = "price of book", example = Constants.Api.FieldExample.PRICE)
    private Double price;

    @Schema(description = "description of book", example = Constants.Api.FieldExample.DESCRIPTION)
    private String description;

    @Schema(description = "translator of book", example = Constants.Api.FieldExample.TRANSLATOR)
    private String translator;

    @Schema(description = "quantity stock of book", example = Constants.Api.FieldExample.ID)
    private Integer quantityStock;

    @Schema(description = "Image of book", example = Constants.Api.FieldExample.IMAGE)
    private String image;
}
