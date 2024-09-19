package team_project.clat.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team_project.clat.domain.Enum.Emoticon;

@Entity
@Getter
@Table(name = "user_like")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    private Message message;  //양방향 매핑 메세지 삭제시 해당 이모티콘도 같이 삭제

    @Enumerated(EnumType.STRING)
    private Emoticon emoticon;


    public Like( Message message, Emoticon emoticon) {
        setMessage(message);
        this.emoticon = emoticon;
    }


    public void setMessage(Message message){
        this.message = message;
        message.getLikes().add(this);
    }


}
