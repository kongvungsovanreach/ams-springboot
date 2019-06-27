package com.kshrd.btb.holymomo.repository;

import com.kshrd.btb.holymomo.repository.FileRepository.FileUploadRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Repository
public class FileUploadRepositoryImp implements FileUploadRepository {
    @Value("${server.file.path}")
    private String SERVER_PATH;

    @Override
    public String uploadFile(MultipartFile file) {
        String fileName = UUID.randomUUID().toString();
        String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        if (!file.isEmpty()){
            try {
                Files.copy(file.getInputStream(), Paths.get(SERVER_PATH+fileName+fileExtension));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName+fileExtension;
    }

    @Override
    public List<String> uploadFiles(List<MultipartFile> files) {
        if(!files.isEmpty()){
            for(MultipartFile file:files) {
                String fileName = UUID.randomUUID().toString();
                String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                try {
                    Files.copy(file.getInputStream(), Paths.get(SERVER_PATH + fileName + fileExtension));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
