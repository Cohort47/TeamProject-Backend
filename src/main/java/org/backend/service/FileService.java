package org.backend.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import org.backend.dto.responseDto.StandardResponseDto;
import org.backend.entity.FileInfo;
import org.backend.repository.FileInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileInfoRepository repository;
    private final AmazonS3 amazonS3;
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Transactional
    @SneakyThrows
    public StandardResponseDto uploadLocalStorage(MultipartFile file) {
        logger.info("Starting upload to local storage...");

        Path fileStorageLocation = Paths.get("src/main/resources/static/upload");
        String originalFileName = file.getOriginalFilename(); // получаем исходное имя фала

        String extension = "";

        if (originalFileName != null) {
            int indexExtension = originalFileName.lastIndexOf(".") + 1; // получаем индекс начала расширения файла
            extension = originalFileName.substring(indexExtension);
        } else {
            throw new NullPointerException("null original file name");
        }

        String uuid = UUID.randomUUID().toString(); // генерация случайной строки в формате UUID
        String newFileName = uuid + "." + extension; // создание нового имени файла

        Path targetLocation = fileStorageLocation.resolve(newFileName);

        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        String link = targetLocation.toString();
        FileInfo fileInfo = new FileInfo();
        fileInfo.setLink(link);
        repository.save(fileInfo);

        return new StandardResponseDto("File " + link + " saved successfully");

    }

    @Transactional
    @SneakyThrows
    public StandardResponseDto upload(MultipartFile file) {

        logger.info("Starting upload to S3...");
        String originalFileName = file.getOriginalFilename(); // получаем исходное имя фала

        String extension = "";

        if (originalFileName != null) {
            int indexExtension = originalFileName.lastIndexOf(".") + 1; // получаем индекс начала расширения файла
            extension = originalFileName.substring(indexExtension);
        } else {
            throw new NullPointerException("null original file name");
        }

        String uuid = UUID.randomUUID().toString(); // генерация случайной строки в формате UUID
        String newFileName = uuid + "." + extension; // создание нового имени файла

        // загрузка в digital Ocean

        InputStream inputStream = file.getInputStream();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());

        // создаем запрос на отправку файла

        PutObjectRequest request = new PutObjectRequest(
                "demo-shop-files",
                "data/" + newFileName,
                inputStream,
                metadata
        ).withCannedAcl(CannedAccessControlList.PublicRead);

        amazonS3.putObject(request);

        String link = amazonS3.getUrl("demo-shop-files", "data/" + newFileName).toString();

        FileInfo fileInfo = new FileInfo().builder()
                .link(link)
                .user(userService.getCurrentUser())
                .build();

        repository.save(fileInfo);

        return new StandardResponseDto("File" + link + " saved successfully");

    }


}
