package com.proconnect.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.proconnect.dto.file.FileRequestDTO;
import com.proconnect.dto.file.FileResponseDTO;
import com.proconnect.entity.File;
import com.proconnect.entity.User;
import com.proconnect.repository.FileRepository;
import com.proconnect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileService {

    private final Cloudinary cloudinary;
    private final FileRepository fileRepository;
    private final UserRepository userRepository;

    @SuppressWarnings("rawtypes")
	public FileResponseDTO uploadFile(MultipartFile multipartFile, FileRequestDTO request) throws IOException {
        // Upload file to Cloudinary
        Map uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(),
                ObjectUtils.asMap("resource_type", "auto"));

        String fileUrl = uploadResult.get("secure_url").toString();

        User user = userRepository.findById(request.getUploadedById())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        File file = File.builder()
                .fileType(request.getFileType())
                .url(fileUrl)
                .uploadedBy(user)
                .uploadedAt(LocalDateTime.now())
                .build();

        fileRepository.save(file);

        return FileResponseDTO.builder()
                .id(file.getId())
                .fileType(file.getFileType())
                .url(file.getUrl())
                .uploadedById(user.getId())
                .uploadedByName(user.getFirstName() + " " + user.getLastName())
                .uploadedAt(file.getUploadedAt())
                .build();
    }
}
