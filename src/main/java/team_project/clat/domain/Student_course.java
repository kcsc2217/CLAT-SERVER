package team_project.clat.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student_course {

    @Id
    @GeneratedValue
    @Column(name = "student_course_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;


    public Student_course(Course course, Member member) {
      setMember(member);
      setCourse(course);
    }

    public void setMember(Member member) { //멤버와 연관관계 설정
        this.member = member;
        member.getStudent_courseList().add(this);
    }

    public void setCourse(Course course) { //강의와 연관관계 설정
        this.course = course;
        course.getStudentCourseList().add(this);
    }
}
