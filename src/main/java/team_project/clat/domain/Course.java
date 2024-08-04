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
    @GeneratedValue
    @Column(name = "course_id")
    private Long id;

    private String courseCode; //학수번호

    private String room; //강의실

    private String timeTable; //시간표

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    List<Student_course> studentCourseList = new ArrayList<>();


    @OneToOne(mappedBy = "course" , cascade = CascadeType.ALL, orphanRemoval = true)
    private ChatRoom chatRoom;



    public Course(String courseCode, String timeTable, String room) {
        this.courseCode = courseCode;
        this.timeTable = timeTable;
        this.room = room;
    }


    public void addChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
        chatRoom.addCourse(this);
    }


}
