package team_project.clat.service;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
// import team_project.clat.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.Member;
import team_project.clat.domain.Report;
import team_project.clat.dto.ReportRequestDTO;
import team_project.clat.exception.GlobalException;
import team_project.clat.repository.ReportRepository;
import team_project.clat.type.ErrorCode;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ReportService {
  @Autowired
  private final ReportRepository reportRepository;

//  @Autowired
//  private final MemberRepository memberRepository;

  @Transactional
  public ReportRequestDTO createReport(ReportRequestDTO reportDTO) {

    String email = reportDTO.getEmail();
    Member member = null;


  /*  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = null;

    if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      username = userDetails.getUsername();
    }

    Member member = memberRepository.findByUsername(username); // username으로 member 조회

    if (member != null) {
      email = member.getEmail();
    }*/

    Report report = new Report(
            null,
            email,
            reportDTO.getDescription(),
            member
    );

    if (email == null || email.isEmpty()) {
      throw new GlobalException(ErrorCode.NULL_EMAIL_INPUT);
    }


    return convertToDTO(reportRepository.save(report));
  }

  @Transactional
  public ReportRequestDTO convertToDTO(Report report) {
    ReportRequestDTO dto = new ReportRequestDTO();
    dto.setId(report.getId());
    dto.setEmail(report.getEmail());
    dto.setDescription(report.getDescription());
    return dto;
  }
}
