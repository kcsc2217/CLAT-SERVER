package team_project.clat.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team_project.clat.domain.Course;
import team_project.clat.domain.Member;
import team_project.clat.domain.Message;
import team_project.clat.dto.response.CourseHomeResDTO;
import team_project.clat.dto.response.MemberAnswerResDTO;
import team_project.clat.dto.response.MemberMessageResDTO;
import team_project.clat.service.MessageService;
import team_project.clat.service.Student_Course_Service;
import team_project.clat.service.TokenService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/home")

public class HomeController {

    private final Student_Course_Service studentCourseService;
    private final TokenService tokenService;
    private final MessageService messageService;

    @GetMapping("")
    public List<CourseHomeResDTO> selectCourse(HttpServletRequest request, @RequestParam("term") String term){

        log.info("해당 멤버의 수업 조회");

        Member findMember = tokenService.getUsernameFromToken(request);

        return  studentCourseService.courseList(findMember.getId(), term);
    }


    @GetMapping("/messages")
    public List<MemberMessageResDTO> memberSelectMessage(HttpServletRequest request){
        Member findMember = tokenService.getUsernameFromToken(request);

        return messageService.memberSelectMessage(findMember.getId());
    }

    @GetMapping("/answer")
    public List<MemberAnswerResDTO> memberSelectAnswer(HttpServletRequest request){

        Member usernameFromToken = tokenService.getUsernameFromToken(request);

        return messageService.findByWithAnswer(usernameFromToken.getId());
    }









}
