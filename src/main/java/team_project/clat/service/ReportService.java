package team_project.clat.service;

import jakarta.mail.MessagingException;
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

  @Autowired
  private EmailService emailService;

  @Transactional
  public ReportRequestDTO createReport(ReportRequestDTO reportDTO, Member member) throws MessagingException{

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

    report = reportRepository.save(report);
    String title = "고객센터 문의입니다.";

    String body = "";
    if (member != null) {
      body += "member id: " + member.getId() + "\n";
    }
    body +=
            "답변받을 이메일: " + report.getEmail() + "\n\n" +
            "문의 내용: " + report.getDescription() + "\n";

    String attachmentPath = report.getFilepath();

    emailService.sendEmailWithAttachment(title, body, attachmentPath);

    return convertToDTO(report);
  }

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
