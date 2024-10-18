package team_project.clat.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memo_id")
    private Memo memo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_id")
    private Answer answer;


    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();


    public void addChatRoom(ChatRoom chatRoom){
        this.chatRoom=chatRoom;
        chatRoom.getMessageList().add(this);
    }

    public void addMemo(Memo memo){
        this.memo=memo;
    }


    public void addAnswer(Answer answer){
        this.answer=answer;
    }

    // 메세지와 멤버 연관관계 메서드 
    public void addMember(Member member){
        this.member=member;
        member.getMessageList().add(this);
    }


    public Message(String message, ChatRoom chatRoom, Member member) { // 일반 메시지 생성 생성자
        this.message = message;

        addChatRoom(chatRoom);
        addMember(member);

    }

    public Message(Member member, ChatRoom chatRoom, List<Image> images){  //파일 메시지 생성 생성자
        addMember(member);
        addChatRoom(chatRoom);
        this.images = images;

        for(Image image:images){
            image.setMessage(this);
        }
    }

    public static Message createMessage(Member member, ChatRoom chatRoom, String message) {
        return new Message(message, chatRoom, member);
    }

    public static Message creteFilePathMessage(Member member, ChatRoom chatRoom, List<Image> images){
        return new Message(member,chatRoom, images);
    }
}
