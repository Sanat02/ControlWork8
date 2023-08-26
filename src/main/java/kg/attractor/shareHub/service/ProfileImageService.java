package kg.attractor.shareHub.service;

import kg.attractor.shareHub.dao.ProfileImageDao;
import kg.attractor.shareHub.dto.ProfileImageDto;
import kg.attractor.shareHub.model.ProfileImage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileImageService {
    private static final String SUB_DIR = "/images";
    private final FileService fileService;
    private final ProfileImageDao profileImageDao;

    public void uploadImage(ProfileImageDto profileImageDto) {
        String fileName = fileService.saveUploadedFile(profileImageDto.getFile(), SUB_DIR);
        ProfileImage pi = ProfileImage.builder()
                .userId(profileImageDto.getUserId())
                .fileName(fileName)
                .id(profileImageDto.getId())
                .status(profileImageDto.getStatus())
                .build();
        profileImageDao.save(pi);
        log.info("Image saved:" + pi.getFileName());

    }


    public List<ProfileImageDto> getImageByUserId(int userId) {
        List<ProfileImage> profileImages = profileImageDao.getImageByUserId(userId);
        List<ProfileImageDto> profileImageDtos = profileImages.stream()
                .map(e -> ProfileImageDto.builder()
                        .id(e.getId())
                        .fileName(e.getFileName())
                        .userId(e.getUserId())
                        .status(e.getStatus())
                        .build()
                ).collect(Collectors.toList());
        return profileImageDtos;
    }

    public ResponseEntity<List<?>> getImageByUsId(int userId) {
        List<ProfileImage> images = profileImageDao.getImageByUserId(userId);
        List<ResponseEntity<?>> responseEntities = new ArrayList<>();

        for (ProfileImage image : images) {
            responseEntities.add(fileService.getOutputFile(image.getFileName(), SUB_DIR, MediaType.IMAGE_JPEG));
        }

        return ResponseEntity.ok(responseEntities);
    }

    public List<ProfileImageDto> getAllFiles() {
        List<ProfileImage> profileImages = profileImageDao.getAllFiles();
        List<ProfileImageDto> profileImageDtos = profileImages.stream()
                .map(e -> ProfileImageDto.builder()
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
