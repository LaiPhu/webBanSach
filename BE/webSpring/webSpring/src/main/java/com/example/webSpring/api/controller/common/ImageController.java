package com.example.webSpring.api.controller.common;

import com.example.webSpring.api.commons.ConstUrl;
import com.example.webSpring.api.core.ApiResponseEntity;
import com.example.webSpring.api.core.Constants;
import com.example.webSpring.api.security.Api.SecureApi;
import com.example.webSpring.api.service.common.ImageService;
import com.example.webSpring.api.vm.admin.AdminAddBookVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Api(tags = Constants.Api.Tag.IMAGE)
@RequestMapping(Constants.Api.Path.PREFIX + ConstUrl.URL_IMAGE)
@RequiredArgsConstructor
@RestController
public class ImageController {

    private final ImageService imageService;

    @ApiOperation(value = "Add image", response = ApiResponseEntity.BodyResponse.class, tags = Constants.Api.Tag.IMAGE_MANAGEMENT)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "New image success",
                            content = @Content(schema = @Schema(implementation = ApiResponseEntity.BodyResponse.class))
                    ),
            }
    )
    @SecureApi
    @PostMapping(value = ConstUrl.URL_ADD, consumes = { MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" })
    public ApiResponseEntity addImage(@Valid @RequestParam("file") MultipartFile file) throws Exception {
        return ApiResponseEntity.bodyOk().bodyData(imageService.uploadImage(file)).build();
    }

}
