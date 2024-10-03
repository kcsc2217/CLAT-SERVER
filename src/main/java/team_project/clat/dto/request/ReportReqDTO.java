package team_project.clat.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportReqDTO {
  private Long id;
  private String email;
  private String description;
  private Long memberId;
  private String filepath;
}