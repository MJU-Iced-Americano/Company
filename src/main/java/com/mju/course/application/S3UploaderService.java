package com.mju.course.application;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.mju.course.domain.model.other.Exception.CourseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static com.mju.course.domain.model.other.Exception.ExceptionList.ERROR_S3_OBJECT_DELETE;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3UploaderService {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(MultipartFile multipartFile, String dirName, String basicFileName) throws IOException {
        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));
        return upload(uploadFile, dirName, basicFileName);
    }

    // S3로 파일 업로드
    private String upload(File uploadFile, String dirName, String basicFileName) {

//        String fileName = dirName + "/" + UUID.randomUUID().toString() + "-" + uploadFile.getName(); // s3에 저장된 파일 이름
        LocalDate now = LocalDate.now();
        String fileName = dirName + "/" + basicFileName;
        String uploadImageUrl = putS3(uploadFile, fileName);

        // 동영상 시간 추출

        removeNewFile(uploadFile);
        return fileName;
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    /**
     * @description 로컬에 저장된 파일 지우기
     */
    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
            return;
        }
        log.info("파일이 삭제되지 못했습니다.");
    }

    /**
     * @description 로컬에 파일 형태로 저장 - S3에 MultipartFile 타입은 전송 x
     * @param multipartFile
     */
    private Optional<File> convert(MultipartFile multipartFile) throws IOException {
        File convertFile = new File(multipartFile.getOriginalFilename());
        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(multipartFile.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

    /**
     * @description s3에서 객체 삭제
     * @param objectKey
     */
    public void deleteS3File(String objectKey) {
        try{
            amazonS3.deleteObject(new DeleteObjectRequest(bucket, objectKey));
        }catch (AmazonServiceException e){
            throw new CourseException(ERROR_S3_OBJECT_DELETE);
        }catch (SdkClientException e){
            throw new CourseException(ERROR_S3_OBJECT_DELETE);
        }
    }

}