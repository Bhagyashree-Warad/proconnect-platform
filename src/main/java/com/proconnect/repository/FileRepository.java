package com.proconnect.repository;

import com.proconnect.entity.File;
import com.proconnect.entity.User;
import com.proconnect.enums.FileType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findByUploadedBy(User user);
    List<File> findByFileType(FileType fileType);
}
