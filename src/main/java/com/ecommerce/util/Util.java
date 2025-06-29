package com.ecommerce.util;

import com.ecommerce.error.exception.FileStorageException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@UtilityClass
@Slf4j
public class Util {

    public static Timestamp currentTimestamp() {
        return Timestamp.from(Instant.now());
    }

//    public static Path saveFile(MultipartFile file, Path uploadDir) {
//        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//        try {
//            if (!Files.exists(uploadDir)) {
//                Files.createDirectories(uploadDir);
//            }
//            Path target = uploadDir.resolve(filename);
//            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
//            return target;
//        } catch (IOException ex) {
//            log.error(ex.getMessage());
//            throw new FileStorageException("Could not store file "+ filename + ". Please try again!", "File storage error");
//        }
//    }

    /**
     * Saves the uploaded file to the specified directory with a unique filename.
     *
     * @param file      the file to be saved
     * @param uploadDir the directory where the file should be saved
     * @return the path of the saved file
     */
    public static String saveFile(MultipartFile file, Path uploadDir) {
        String originalFilename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String fileExtension = originalFilename.contains(".") ? originalFilename.substring(originalFilename.lastIndexOf(".")) : "";
        String uniqueFilename = UUID.randomUUID().toString() + fileExtension;

        try {
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
            Path target = uploadDir.resolve(uniqueFilename);
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
            return uniqueFilename;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file "+ uniqueFilename + ". Please try again!", "File storage error");        }
    }

}
