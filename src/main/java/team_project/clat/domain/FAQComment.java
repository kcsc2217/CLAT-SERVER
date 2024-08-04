package team_project.clat.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FAQComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faqComment_id")
    private Long id;
    private String description;

    @JsonIgnore
    @OneToOne(mappedBy = "faqComment", fetch = FetchType.LAZY)
    private FAQItem faqItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public FAQComment(Long id, String description, FAQItem faqItem) {
        this.id = id;
        this.description = description;
        this.faqItem = faqItem;
    }
}
