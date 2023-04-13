package com.example.webSpring.api.vm.user;

import com.example.webSpring.api.core.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class BookVM {

    @Schema(description = "Id of book", example = Constants.Api.FieldExample.ID, required = true)
    @NotBlank
    private String id;

    @Schema(description = "Id of author", example = Constants.Api.FieldExample.ID, required = true)
    @NotBlank
    private String authorId;

    @Schema(description = "Id of category", example = Constants.Api.FieldExample.ID, required = true)
    @NotBlank
    private String categoryId;

    @Schema(description = "Id of publishing company", example = Constants.Api.FieldExample.ID, required = true)
    @NotBlank
    private String publishingCompanyId;

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
