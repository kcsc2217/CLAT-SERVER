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
public class Report extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    private String title;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private ReportComment reportComment;

    public Report(Long id, String title, String description, Member member) {
        this.id = id;
        this.title = title;
        this.description = description;
        addMember(member);
    }

    //연관관계 메서드
    public void addMember(Member member){
        this.member = member;
        member.getReportList().add(this);
    }

    public void setReportComment(ReportComment reportComment){
        this.reportComment = reportComment;
    }


}
