package team_project.clat.domain.Enum;

public enum UserType {

    STUDENT("ROLE_STUDENT"),
    PROFESSOR("ROLE_PROFESSOR"),
    ADMIN("ROLE_ADMIN");

    private String description;

    UserType(String description) {
        this.description = description;
    }

    public String getDescription() {return description;}
}
