package team_project.clat.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class ImageReqDTO {
    List<MultipartFile> images = new ArrayList<>();

    public ImageReqDTO(List<MultipartFile> images) {
        this.images = images;
    }

    public ImageReqDTO() {
    }
}
