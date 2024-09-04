package team_project.clat.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team_project.clat.domain.Member;
import team_project.clat.jwt.JwtUtil;
import team_project.clat.repository.MemberRepository;
import team_project.clat.service.ReportService;
import team_project.clat.dto.ReportRequestDTO;

import java.util.List;

@Tag(name = "report API", description = "report API")
@Slf4j
@RestController
@RequestMapping("/help/report")
@RequiredArgsConstructor
public class ReportController {

  private final ReportService reportService;

  private final JwtUtil jwtUtil;

  private final MemberRepository memberRepository;

  @GetMapping
  public ResponseEntity<List<ReportRequestDTO>> getReports() {
    return ResponseEntity.ok(reportService.getReports());
  }

  @PostMapping
  public ResponseEntity<ReportRequestDTO> createFAQComment(@RequestBody ReportRequestDTO reportRequestDTO, HttpServletRequest request) {
    String accessToken = request.getHeader("access");
    Member member = null;

    if (accessToken != null) {
      member = memberRepository.findByUsername(jwtUtil.getUsername(accessToken));
    }

    return ResponseEntity.ok(reportService.createReport(reportRequestDTO, member));
  }

}
