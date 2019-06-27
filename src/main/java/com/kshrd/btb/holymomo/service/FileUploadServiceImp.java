package com.kshrd.btb.holymomo.service;

import com.kshrd.btb.holymomo.repository.FileRepository.FileUploadRepository;
import com.kshrd.btb.holymomo.service.ArticleService.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public class FileUploadServiceImp implements FileUploadService {
    @Autowired
    private FileUploadRepository fileUploadRepository;
    @Override
    public String uploadFile(MultipartFile file) {
        return fileUploadRepository.uploadFile(file);
    }

    @Override
    public List<String> uploadFiles(List<MultipartFile> files) {
        return fileUploadRepository.uploadFiles(files);
    }
}
