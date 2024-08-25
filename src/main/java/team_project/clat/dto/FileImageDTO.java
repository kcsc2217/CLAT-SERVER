package team_project.clat.dto;


import lombok.Data;

@Data
public class FileImageDTO {
    private Long imageId;
    private String imageURL;

    public FileImageDTO(Long imageId, String imageURL) {
        this.imageId = imageId;
        this.imageURL = imageURL;
    }

    public FileImageDTO() {
    }
}
