package kg.attractor.shareHub.service;

import kg.attractor.shareHub.dao.FileListDao;
import kg.attractor.shareHub.dto.FileListDto;
import kg.attractor.shareHub.model.File;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileListService {
    private static final String SUB_DIR = "/images";
    private final FileService fileService;
    private final FileListDao fileListDao;

    public void uploadImage(FileListDto profileImageDto) {
        String fileName = fileService.saveUploadedFile(profileImageDto.getFile(), SUB_DIR);
        File pi = File.builder()
                .userId(profileImageDto.getUserId())
                .fileName(fileName)
                .id(profileImageDto.getId())
                .status(profileImageDto.getStatus())
                .build();
        fileListDao.save(pi);
        log.info("Image saved:" + pi.getFileName());

    }


    public List<FileListDto> getImageByUserId(int userId) {
        List<File> files = fileListDao.getImageByUserId(userId);
        List<FileListDto> fileListDtos = files.stream()
                .map(e -> FileListDto.builder()
                        .id(e.getId())
                        .fileName(e.getFileName())
                        .userId(e.getUserId())
                        .status(e.getStatus())
                        .build()
                ).collect(Collectors.toList());
        log.info("Got images of user :" + userId);
        return fileListDtos;
    }

    public Page<FileListDto> getAllFiles(int start, int end) {
        List<File> files = fileListDao.getAllFiles();
        List<FileListDto> fileListDtos = files.stream()
                .map(e -> FileListDto.builder()
                        .id(e.getId())
                        .fileName(e.getFileName())
                        .userId(e.getUserId())
                        .status(e.getStatus())
                        .build()
                ).collect(Collectors.toList());
        log.info("Got all files");
        var page = toPage(fileListDtos, PageRequest.of(start, end));
        return page;
    }

    private Page<FileListDto> toPage(List<FileListDto> list, Pageable pageable) {
        if (pageable.getOffset() >= list.size()) {
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = (int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ?
                list.size() :
                pageable.getOffset() + pageable.getPageSize());
        List<FileListDto> subList = list.subList(startIndex, endIndex);
        return new PageImpl<>(subList, pageable, list.size());
    }

    public void deleteFile(int fileId) {
        fileListDao.delete(fileId);
    }
}
