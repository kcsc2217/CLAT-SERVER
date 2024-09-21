package team_project.clat.domain.Enum;

public enum Emoticon {

    HEART("사랑"),
    SAD("슬픔"),
    LIKE("좋아요");

    private String description;

    Emoticon(String description) {
        this.description = description;
    }
    public String getDescription() {return this.description; }
}
