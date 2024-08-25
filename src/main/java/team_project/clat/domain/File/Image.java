package team_project.clat.domain.File;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import team_project.clat.domain.Message;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    private String originName; //이미지 파일 본래이름

    private String storedName; // 이미지 파일이 s3에 저장될 떄 사용할 이름

    private String accessUrl; // 내부 s3 접근 할 수 있는 url

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    private Message message;

    public Image(String originName) {
        this.originName = originName;
        this.storedName = getFileName(originName);
        this.accessUrl = "";
    }
    public void setAccessUrl(String accessUrl){
        this.accessUrl = accessUrl;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setId(Long imageId){
        this.id = imageId;
    }



    public String getFileName(String originName){
        String ext = extractExtension(originName);
        String uuid = UUID.randomUUID().toString();

        return uuid + "." + ext;
    }

    public String extractExtension(String originName){
        int index = originName.lastIndexOf('.');
        return originName.substring(index+1);
    }
}
