package team_project.clat.dto;

import lombok.Data;

import java.util.List;

@Data
public class ImageResponseDTO {

    private List<String> imageUrl;

    public ImageResponseDTO(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ImageResponseDTO() {
    }
}
