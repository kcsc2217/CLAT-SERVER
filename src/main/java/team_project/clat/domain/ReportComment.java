package team_project.clat.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter

public class ReportComment extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "reportComment_id")
    private Long id;

    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private Report report;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;  //운영진 답변

    public ReportComment(String description, Report report, Member member) {
        this.description = description;
        setReport(report);
        setMember(member);
    }

    public void setReport(Report report) {
        this.report = report;
        report.setReportComment(this);
    }

    public void setMember(Member member) {
        this.member = member;
        member.getReportCommentList().add(this);
    }
}
