package team_project.clat.controller.help.report;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team_project.clat.service.ReportService;
import team_project.clat.dto.ReportRequestDTO;

@Tag(name = "report API", description = "report API")
@RestController
@RequestMapping("/help/report")
@RequiredArgsConstructor
public class ReportController {

  private final ReportService reportService;

  @PostMapping
  public ResponseEntity<ReportRequestDTO> createFAQComment(@RequestBody ReportRequestDTO reportRequestDTO) {
    return ResponseEntity.ok(reportService.createReport(reportRequestDTO));
  }

}
