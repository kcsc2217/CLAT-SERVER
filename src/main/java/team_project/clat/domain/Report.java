package team_project.clat.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    private String email;   // 신고하기 답변을 받을 수 있는 이메일 정보

    private String description;

    private String filepath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Report(Long id, String email, String description, Member member, String filepath) {
        this.id = id;
        this.email = email;
        this.description = description;
        addMember(member);
        this.filepath = filepath;
    }

    // 연관 관계 메서드
    public void addMember(@Nullable Member member){
        if (member != null) {
            this.member = member;
            member.getReportList().add(this);
        }
    }

}
