package com.kshrd.btb.holymomo.repository.FileRepository;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface FileUploadRepository {
    String uploadFile(MultipartFile file);
    List<String> uploadFiles(List<MultipartFile> files);
}
