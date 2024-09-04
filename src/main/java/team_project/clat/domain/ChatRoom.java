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
public class ChatRoom extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id")
    private Long id;

    private String roomName;

    private int roomKey;

    private int week;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "chatRoom")
    List<Message> messageList = new ArrayList<>();


    public ChatRoom(String roomName,int week ,Course course) {
        this.roomName = roomName;
        this.week = week;
        addCourse(course);
        randomNumber();
    }


    public void randomNumber(){
        this.roomKey = (int)(Math.random() * 8999) + 1000;
    }


    public void addCourse(Course course) {
        this.course = course;
       course.getChatRoomList().add(this);
    }
}
