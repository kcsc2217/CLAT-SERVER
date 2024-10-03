package team_project.clat.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import team_project.clat.domain.Image;
import team_project.clat.dto.response.ImageResDTO;
import team_project.clat.dto.request.ImageReqDTO;
import team_project.clat.exception.ImageDownloadFailedException;
import team_project.clat.repository.ImageRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ImageService {

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    private final AmazonS3Client amazonS3Client;
    private final ImageRepository imageRepository;

    @Transactional
    public List<ImageResDTO> saveImages(ImageReqDTO testImageDto){

        List<ImageResDTO> list = new ArrayList<>();

        for( MultipartFile multipartFile: testImageDto.getImages()){
            ImageResDTO imageResponseDTO = saveImage(multipartFile);
            list.add(imageResponseDTO);
        }
    return list;
    }


    @Transactional
    public ImageResDTO saveImage(MultipartFile multipartFile){
        String originalFilename = multipartFile.getOriginalFilename(); //파일 원래 이름

        Image image = new Image(originalFilename); // 이미지 객체생성
        String storedName = "chat-service/" + image.getStoredName(); // 이미지 내에서 파일 구별 할 수 있게 저장 이미지 경로 만듬

        try{
            ObjectMetadata objectMetadata = new ObjectMetadata(); // S3 에 저장할 메타데이터 객체 생성
            objectMetadata.setContentType(multipartFile.getContentType()); // 업로드된 파일의 MIME 타입을 설정 브라우저가 파일을 처리
            objectMetadata.setContentLength(multipartFile.getInputStream().available()); // S3 에 저장할 파일 크기

            amazonS3Client.putObject(bucketName, storedName, multipartFile.getInputStream(), objectMetadata); // S3에 파일 버켓에 업로드

            String accessUrl = amazonS3Client.getUrl(bucketName, storedName).toString(); // 업로드된 S3 URL 정보 이 정보는 파일을 웹에서 접근가능함
            image.setAccessUrl(accessUrl); // 이미지 객체에 파일 접근 가능한 URL 저장
        }catch (IOException e){

        }

        Image findImage = imageRepository.save(image);

        return new ImageResDTO(findImage.getId(), findImage.getAccessUrl());

    }

    @Transactional
    public byte[] downloadImage(String filePath){
        String[] split = filePath.split("/chat-service/");
        String fileName = "chat-service/" + split[1];
        log.info("{}", fileName);

        S3Object s3Object = amazonS3Client.getObject(bucketName, fileName);
        S3ObjectInputStream s3ObjectContent = s3Object.getObjectContent();

        try {
            return IOUtils.toByteArray(s3ObjectContent);
        } catch (IOException e) {
            throw new ImageDownloadFailedException("파일 다운로드에 실패하였습니다.");
        }
    }

}
