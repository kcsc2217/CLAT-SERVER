package team_project.clat.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Answer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "answer")
    private Message message;    //메세지에 대한 일대일 연관관계 주인


    public Answer(String answer, Member member, Message message) {
        this.answer = answer;
        setMember(member);
        setMessage(message);
    }

    public void setMember(Member member) {
        this.member = member;
        member.getAnswerList().add(this);
    }

    public void setMessage(Message message) {
        this.message = message;
        message.addAnswer(this);
    }
}
