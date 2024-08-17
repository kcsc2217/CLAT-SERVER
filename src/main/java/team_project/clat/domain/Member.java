package team_project.clat.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team_project.clat.domain.Enum.Gender;
import team_project.clat.domain.Enum.UserType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본생성자 protected로
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String password;

    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Embedded
    private Address address;

    private String nickName;

    // 신고하기 와 양방향 맵핑
    @OneToMany(mappedBy = "member" , cascade = CascadeType.ALL, orphanRemoval = true)
    List<Report> reportList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Like> likeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Student_course> student_courseList = new ArrayList<>();



    public Member(Long id, String nickName, String email, String password, int age, Gender gender, UserType userType, Address address) {
        this.id = id;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.userType = userType;
        this.address = address;
    }

    public static class BaseEntity {
    }
}
