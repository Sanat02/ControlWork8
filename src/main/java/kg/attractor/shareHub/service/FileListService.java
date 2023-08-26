package kg.attractor.shareHub.service;

import kg.attractor.shareHub.dao.FileListDao;
import kg.attractor.shareHub.dto.FileListDto;
import kg.attractor.shareHub.model.File;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileListService {
    private static final String SUB_DIR = "/images";
    private final FileService fileService;
    private final FileListDao profileImageDao;

    public void uploadImage(FileListDto profileImageDto) {
        String fileName = fileService.saveUploadedFile(profileImageDto.getFile(), SUB_DIR);
        File pi = File.builder()
                .userId(profileImageDto.getUserId())
                .fileName(fileName)
                .id(profileImageDto.getId())
                .status(profileImageDto.getStatus())
                .build();
        profileImageDao.save(pi);
        log.info("Image saved:" + pi.getFileName());

    }


    public List<FileListDto> getImageByUserId(int userId) {
        List<File> profileImages = profileImageDao.getImageByUserId(userId);
        List<FileListDto> profileImageDtos = profileImages.stream()
                .map(e -> FileListDto.builder()
                        .id(e.getId())
                        .fileName(e.getFileName())
                        .userId(e.getUserId())
                        .status(e.getStatus())
                        .build()
                ).collect(Collectors.toList());
        return profileImageDtos;
    }

    public ResponseEntity<List<?>> getImageByUsId(int userId) {
        List<File> images = profileImageDao.getImageByUserId(userId);
        List<ResponseEntity<?>> responseEntities = new ArrayList<>();

        for (File image : images) {
            responseEntities.add(fileService.getOutputFile(image.getFileName(), SUB_DIR, MediaType.IMAGE_JPEG));
        }

        return ResponseEntity.ok(responseEntities);
    }

    public List<FileListDto> getAllFiles() {
        List<File> profileImages = profileImageDao.getAllFiles();
        List<FileListDto> profileImageDtos = profileImages.stream()
                .map(e -> FileListDto.builder()
                        .id(e.getId())
                        .fileName(e.getFileName())
                        .userId(e.getUserId())
                        .status(e.getStatus())
                        .build()
                ).collect(Collectors.toList());
        return profileImageDtos;
    }

    public void deleteFile(int fileId) {
        profileImageDao.delete(fileId);
    }
}
