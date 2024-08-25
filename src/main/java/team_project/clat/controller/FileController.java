package team_project.clat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team_project.clat.domain.File.ValidationFileSize;
import team_project.clat.dto.ImageResponseDTO;
import team_project.clat.dto.TestImageDto;
import team_project.clat.service.ImageService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class FileController {

    private final ImageService imageService;


    @PostMapping("/image")
    @ResponseStatus(HttpStatus.OK)
    public ImageResponseDTO saveImage(@ModelAttribute TestImageDto testImageDto){

        ValidationFileSize.sizeCheck(testImageDto.getImages());

        log.info("파일 컨트롤러 통과 ");
        log.info("t");
        List<String> strings = imageService.saveImages(testImageDto);

        return new ImageResponseDTO(strings);
    }

}
