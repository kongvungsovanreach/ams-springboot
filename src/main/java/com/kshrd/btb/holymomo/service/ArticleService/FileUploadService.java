package com.kshrd.btb.holymomo.service.ArticleService;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface FileUploadService {
    String uploadFile(MultipartFile file);
    List<String> uploadFiles(List<MultipartFile> files);
}
