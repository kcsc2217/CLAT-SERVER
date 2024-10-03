package team_project.clat.dto.response;

import lombok.Data;

@Data
public class ImageResDTO {

    private Long imageId;
    private String imageUrl;

    public ImageResDTO(Long imageId, String imageUrl) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
    }

    public ImageResDTO() {
    }
}
