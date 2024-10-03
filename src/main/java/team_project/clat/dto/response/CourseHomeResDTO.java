package team_project.clat.dto.response;

import lombok.Data;
import team_project.clat.domain.Course;

import java.util.List;

@Data
public class CourseHomeResDTO {

    private Long courseId;

    private String courseName;

    private String room; //강의실

    private String start_date; //시작 시간표

    private String end_date;

    private List<HomeChatRoomResDTO> chatRooms;



    public CourseHomeResDTO(Course course) {
        this.courseId = course.getId();
        this.courseName = course.getCourseName();
        this.room = course.getRoom();
        this.start_date = course.getTimeTable().getStart_date();
        chatRooms = course.getChatRoomList().stream().map(chatRoom -> new HomeChatRoomResDTO(chatRoom.getId(), chatRoom.getWeek())).toList();
        this.end_date = course.getTimeTable().getEnd_date();
    }
    public CourseHomeResDTO() {
    }
}
