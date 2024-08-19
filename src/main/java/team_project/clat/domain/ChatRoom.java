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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    List<Message> messageList = new ArrayList<>();

    public ChatRoom(String roomName, Course course) {
        this.roomName = roomName;
        addCourse(course);
    }


    public void addCourse(Course course) {
        this.course = course;
        course.addChatRoom(this);
    }
}
