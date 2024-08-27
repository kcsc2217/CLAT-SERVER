package team_project.clat.dto;

import lombok.Data;
import team_project.clat.domain.Course;

@Data
public class CourseHomeDTO {
    private String courseCode;

    private String courseName;

    private String room; //강의실

    private String start_date; //시작 시간표

    private String end_date;

    public CourseHomeDTO(Course course) {
        this.courseCode = course.getCourseCode();
        this.courseName = course.getCourseName();
        this.room = course.getRoom();
        this.start_date = course.getTimeTable().getStart_date();
        this.end_date = course.getTimeTable().getEnd_date();
    }

    public CourseHomeDTO() {
    }
}
