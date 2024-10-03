package team_project.clat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import team_project.clat.domain.File.ValidationFileSize;
import team_project.clat.dto.response.ImageResDTO;
import team_project.clat.dto.request.ImageReqDTO;
import team_project.clat.service.ImageService;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class FileController {

    private final ImageService imageService;


    @PostMapping("/image")
    @ResponseStatus(HttpStatus.OK)
    public List<ImageResDTO> saveImage(@ModelAttribute ImageReqDTO testImageDto){

        ValidationFileSize.sizeCheck(testImageDto.getImages());

        log.info("파일 컨트롤러 통과 ");

    return imageService.saveImages(testImageDto);

    }

    @PostMapping("/download")
    public ResponseEntity<?> downloadImage(@RequestBody Map<String, String> filePathMap){
        String filePath = filePathMap.get("filePath");

        log.info("{}", filePath);
        byte[] imageBytes = imageService.downloadImage(filePath);
        ByteArrayResource resource = new ByteArrayResource(imageBytes);


        String[] split = filePath.split("/chat-service/");
        String fileName = split[1];
        log.info("{}", fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(imageBytes.length);
        headers.setContentType(contentType(fileName));
        headers.setContentDisposition(createContentDisposition(fileName));
        log.info("{}", createContentDisposition(fileName).getFilename());

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(resource);
    }

    public MediaType contentType(String fileName) {

        String[] arr = fileName.split("\\.");
        String type = arr[arr.length-1];

        switch(type) {
            case "txt":
                return MediaType.TEXT_PLAIN;
            case "png":
                return MediaType.IMAGE_PNG;
            case "jpg":
                return MediaType.IMAGE_JPEG;
            case "pdf":
                return MediaType.APPLICATION_PDF;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    public ContentDisposition createContentDisposition(String fileName) {
        return  ContentDisposition.builder("attachment")
                .filename(fileName, StandardCharsets.UTF_8)
                .build();
    }


}
