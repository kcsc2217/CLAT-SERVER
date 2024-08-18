package team_project.clat.domain.response;

public record ErrorResponse (
        String title,
        Integer status,
        Integer code,
        String instance
) {}