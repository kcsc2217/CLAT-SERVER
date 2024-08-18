package team_project.clat.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FAQItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faq_id")
    private Long id;

    private String title;

    private String description;

    private String comment;

    public FAQItem(Long id, String title, String description, String comment) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.comment = comment;
    }
}
