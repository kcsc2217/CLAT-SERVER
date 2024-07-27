package team_project.clat.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MESSAGE_ID")
    private Long id;

    private String message;

    private LocalDateTime messageDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHATROOM_ID")
    private ChatRoom chatRoom;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
    List<Memo> memoList = new ArrayList<>();

    public void addChatRoom(ChatRoom chatRoom){
        this.chatRoom=chatRoom;
        chatRoom.getMessageList().add(this);
    }

    public Message(Long id, String message, LocalDateTime messageDate, ChatRoom chatRoom) {
        this.id = id;
        this.message = message;
        this.messageDate = messageDate;
        this.chatRoom = chatRoom;
    }
}
