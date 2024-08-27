package team_project.clat.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import team_project.clat.domain.Course;
import team_project.clat.domain.Member;
import team_project.clat.dto.CourseHomeDTO;
import team_project.clat.jwt.JwtUtil;
import team_project.clat.repository.MemberRepository;
import team_project.clat.service.Student_Course_Service;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j

public class HomeController {

    private final Student_Course_Service studentCourseService;
    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;

    @GetMapping("/home")
    public List<CourseHomeDTO> selectCourse(HttpServletRequest request){
        String accessToken = request.getHeader("access");
        String username = jwtUtil.getUsername(accessToken);


        Member byUsername = memberRepository.findByUsername(username);

        List<Course> courseList = studentCourseService.courseList(byUsername.getId());

       return courseList.stream().map(CourseHomeDTO::new).collect(Collectors.toList());
    }



}
