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
    @GeneratedValue
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

    @OneToMany(mappedBy = "member" , cascade = CascadeType.ALL, orphanRemoval = true)
    List<Report> reportList = new ArrayList<>();


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
