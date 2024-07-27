package team_project.clat.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMO_ID")
    private Long id;

    private String Memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MESSAGE_ID")
    private Message message;


    public void addMessage(Message message){
        this.message=message;
        message.getMemoList().add(this);
    }

    public Memo(Long id, String memo, Message message) {
        this.id = id;
        Memo = memo;
        this.message = message;
    }
}
