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

    private String name;
    private String username;
    private String email;
    private String password;
    private String schoolName;
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

    // 신고하기 댓글과 양방향 맵핑
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ReportComment> reportCommentList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Like> likeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    List<FAQItem> faqItemList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    List<FAQComment> faqCommentList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Student_course> student_courseList = new ArrayList<>();



    public Member(Long id, String name, String username, String nickName, String email, String password, String schoolName, int age, Gender gender, UserType userType, Address address) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.schoolName = schoolName;
        this.age = age;
        this.gender = gender;
        this.userType = userType;
        this.address = address;
    }

    public static Member memberSet(String name, String username, String password, String schoolName, UserType userType){
        Member member = new Member();

        member.name = name;
        member.username = username;
        member.password = password;
        member.schoolName = schoolName;
        member.userType = userType;

        return member;
    }

    public static Member JwtMemberSet(String username, String password, String userType){
        Member member = new Member();

        member.username = username;
        member.password = password;
        member.userType = UserType.valueOf(userType);

        return member;
    }

    public static class BaseEntity {
    }
}
