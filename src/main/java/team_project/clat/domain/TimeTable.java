package team_project.clat.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeTable {

    private String start_date; //시작 시간표

    private String end_date; //끝 시간표

    public TimeTable(String start_date, String end_date) {
        this.start_date = start_date;
        this.end_date = end_date;
    }
}
