package team_project.clat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team_project.clat.domain.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {}