package team_project.clat.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @Column(name = "course_code")
    private String courseCode; //학수번호

    @Column(name = "course_name")
    private String courseName;

    private String room; //강의실

    @Embedded
    private TimeTable timeTable;



    private String dayOfWeek;    // 요일


    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    List<Student_course> studentCourseList = new ArrayList<>();


    @OneToMany(mappedBy = "course" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatRoom> chatRoomList = new ArrayList<>();



    public Course(String room, String courseCode, TimeTable timeTable, String course_name, String dayOfWeek) {
        this.room = room;
        this.courseCode = courseCode;
        this.timeTable = timeTable;
        this.courseName = course_name;
        this.dayOfWeek = dayOfWeek;
    }


}