package team_project.clat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team_project.clat.domain.FAQItem;

public interface FAQItemRepository extends JpaRepository<FAQItem, Long> {}