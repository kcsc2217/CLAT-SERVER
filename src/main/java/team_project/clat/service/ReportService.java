package team_project.clat.service;

import org.springframework.security.access.prepost.PreAuthorize;
import team_project.clat.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.Report;
import team_project.clat.dto.ReportRequestDTO;
import team_project.clat.exception.GlobalException;
import team_project.clat.repository.ReportRepository;
import team_project.clat.type.ErrorCode;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ReportService {
  @Autowired
  private final ReportRepository reportRepository;

  @Transactional
  public ReportRequestDTO createReport(ReportRequestDTO reportDTO, Member member) {

    String email = reportDTO.getEmail();

    if ((member != null) && (email == null || email.isEmpty())) {
      if ((email = member.getEmail()) == null) {
          throw new GlobalException(ErrorCode.NULL_EMAIL_INPUT);
      }
    }

    Report report = new Report(
            null,
            email,
            reportDTO.getDescription(),
            member,
            reportDTO.getFilepath()
    );


    return convertToDTO(reportRepository.save(report));
  }

  @PreAuthorize("hasRole('ADMIN')")
  public List<ReportRequestDTO> getReports() {
    return reportRepository.findAll().stream()
          .map(this::convertToDTO)
            .collect(Collectors.toList()); }


  @Transactional
  public ReportRequestDTO convertToDTO(Report report) {
    ReportRequestDTO dto = new ReportRequestDTO();
    dto.setId(report.getId());
    dto.setEmail(report.getEmail());
    dto.setDescription(report.getDescription());
    dto.setFilepath(report.getFilepath());
    if (report.getMember() != null) {
      dto.setMemberId(report.getMember().getId());
    }
    return dto;
  }
}
