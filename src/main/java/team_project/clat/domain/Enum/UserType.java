package team_project.clat.domain.Enum;

public enum UserType {

    STUDENT("학생"),
    PROFESSOR("교수"),
    ADMIN("운영진");

    private String description;

    UserType(String description) {
        this.description = description;
    }

    public String getDescription() {return description;}
}
