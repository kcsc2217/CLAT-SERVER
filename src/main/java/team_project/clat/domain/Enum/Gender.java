package team_project.clat.domain.Enum;

public enum Gender {

    MALE("남성"),
    FEMALE("여성");

    private String description;

    Gender(String description) {this.description = description;}

    public String getDescription() {return description;}
}
