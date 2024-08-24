package team_project.clat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team_project.clat.dto.TestImageDto;
import team_project.clat.service.ImageService;

import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@Slf4j
public class FileTestController {

    private final ImageService imageService;


    @PostMapping("/image")
    @ResponseStatus(HttpStatus.OK)
    public List<String> saveImage(@ModelAttribute TestImageDto testImageDto){
        log.info("파일 컨트롤러 통과 ");
        log.info("t");
        return imageService.saveImages(testImageDto);
    }

}
