package team_project.clat.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHATROOM_ID")
    private Long id;

    private String roomName;

    @OneToMany(mappedBy = "chatroom", cascade = CascadeType.ALL)
    List<Message> messageList = new ArrayList<>();

    public ChatRoom(Long id, String roomName) {
        this.id = id;
        this.roomName = roomName;
    }
}
