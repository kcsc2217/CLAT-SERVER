package team_project.clat.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestImageDto {
    List<MultipartFile> images = new ArrayList<>();

    public TestImageDto(List<MultipartFile> images) {
        this.images = images;
    }

    public TestImageDto() {
    }
}
