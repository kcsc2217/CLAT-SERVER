package team_project.clat.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memo_id")
    private Long id;

    private String memoContent;


    public Memo(String memo) {
        memoContent = memo;
    }


    public void  updateMemo(String memoContent){
        this.memoContent = memoContent;
    }


}
