package team_project.clat.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)


public class ChatRoomMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatRoomMember_id")
    private Long chatRoomMemberId;

    private boolean isPassWorldEnter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //단방향 매핑


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatRoom_id")
    private ChatRoom chatRoom; //단방향 매핑

    @Builder
    public ChatRoomMember(boolean isPassWorldEnter, ChatRoom chatRoom, Member member) {
        this.isPassWorldEnter = isPassWorldEnter;
        this.chatRoom = chatRoom;
        this.member = member;
    }
}
