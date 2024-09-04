package team_project.clat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequestDTO {
  private Long id;
  private String email;
  private String description;
  private Long memberId;
  private String filepath;
}