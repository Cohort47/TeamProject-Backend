package org.backend.controller;

import lombok.RequiredArgsConstructor;

import org.backend.dto.responseDto.StandardResponseDto;
import org.backend.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class FilesController {

    private final FileService service;

    @PostMapping("/api/files")
    public StandardResponseDto upload(@RequestParam("uploadFile")MultipartFile file){
        return service.upload(file);
    }
}
