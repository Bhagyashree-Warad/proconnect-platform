package com.proconnect.controller;

import com.proconnect.dto.file.FileRequestDTO;
import com.proconnect.dto.file.FileResponseDTO;
import com.proconnect.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<FileResponseDTO> uploadFile(
            @RequestPart("file") MultipartFile file,
            @RequestPart("info") FileRequestDTO request
    ) throws IOException {
        FileResponseDTO response = fileService.uploadFile(file, request);
        return ResponseEntity.ok(response);
    }
}
