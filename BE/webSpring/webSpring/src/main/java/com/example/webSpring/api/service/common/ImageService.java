package com.example.webSpring.api.service.common;

import com.example.webSpring.api.model.common.Image;
import com.example.webSpring.api.repository.common.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {

    private final ImageRepository imageRepository;

    public Image uploadImage(MultipartFile file) throws Exception {
        Image image = new Image();
        image.setFileName(file.getOriginalFilename());
        image.setFileType(file.getContentType());
        image.setData(file.getBytes());
        return imageRepository.save(image);
    }

}
