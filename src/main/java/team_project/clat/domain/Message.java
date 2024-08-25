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
public class Message extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    private String message;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
    List<Memo> memoList = new ArrayList<>();

    private String senderName;

    private String imageUrl;


    public void addChatRoom(ChatRoom chatRoom){
        this.chatRoom=chatRoom;
        chatRoom.getMessageList().add(this);
    }


    public Message(String message, ChatRoom chatRoom, String senderName) {
        this.message = message;
        addChatRoom(chatRoom);
        this.senderName=senderName;

    }

    public Message(ChatRoom chatRoom, String senderName, String imageUrl) {
        addChatRoom(chatRoom);
        this.senderName = senderName;
        this.imageUrl = imageUrl;
    }

    public static Message createMessage(String senderName, ChatRoom chatRoom, String message) {
        return new Message(message, chatRoom, senderName);
    }

    public static Message creteFilePathMessage(String senderName, ChatRoom chatRoom, String imageUrl){
        return new Message(chatRoom, senderName, imageUrl);
    }
}
