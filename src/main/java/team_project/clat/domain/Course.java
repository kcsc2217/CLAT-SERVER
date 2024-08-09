package team_project.clat.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    private String courseCode; //학수번호

    private String room; //강의실

    @Embedded
    private TimeTable timeTable;

    private String Course_name; //강의명

    private String dayOfWeek;    // 요일

    //시간표

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    List<Student_course> studentCourseList = new ArrayList<>();


    @OneToOne(mappedBy = "course" , cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ChatRoom chatRoom;


    public Course(String room, String courseCode, TimeTable timeTable, String course_name, String dayOfWeek) {
        this.room = room;
        this.courseCode = courseCode;
        this.timeTable = timeTable;
        Course_name = course_name;
        this.dayOfWeek = dayOfWeek;
    }

    public void addChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }


}
