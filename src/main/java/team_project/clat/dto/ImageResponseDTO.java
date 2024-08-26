package team_project.clat.dto;

import lombok.Data;

import java.util.List;

@Data
public class ImageResponseDTO {

    private Long imageId;
    private String imageUrl;

    public ImageResponseDTO(Long imageId, String imageUrl) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
    }

    public ImageResponseDTO() {
    }
}
