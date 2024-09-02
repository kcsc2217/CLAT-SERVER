package team_project.clat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import team_project.clat.domain.Enum.UserType;

@Getter
@Setter
public class JoinDto {

    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String name;

    @NotBlank(message = "아이디는 필수 입력값입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{8,14}",
            message = "비밀번호는 영문 소문자와 숫자, 특수기호를 8자~14자 이하로 조합해주세요.")
    private String password;

    @NotBlank(message = "학교/기관은 필수 입력값입니다.")
    private String schoolName;

    @NotBlank(message = "회원구분은 필수 입력값입니다.")
    private UserType userType;
}
