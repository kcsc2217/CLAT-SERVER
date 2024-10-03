package team_project.clat.dto.response;


import lombok.Data;

@Data
public class FileImageResDTO {
    private Long imageId;
    private String imageURL;

    public FileImageResDTO(Long imageId, String imageURL) {
        this.imageId = imageId;
        this.imageURL = imageURL;
    }

    public FileImageResDTO() {
    }
}
