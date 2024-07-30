package team_project.clat.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FAQItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faq_id")
    private Long id;

    private String title;
    private String description;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="faqComment_id")
    private FAQComment faqComment;

    public FAQItem(Long id, String title, String description, FAQComment faqComment) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.faqComment = faqComment;
    }
}
