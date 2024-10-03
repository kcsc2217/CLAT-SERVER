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

        List<Course> courseList = studentCourseService.courseList(findMember.getId(), term);

       return courseList.stream().map(CourseHomeResDTO::new).collect(Collectors.toList());
    }


    @GetMapping("/messages")
    public List<MemberMessageResDTO> memberSelectMessage(HttpServletRequest request){
        Member findMember = tokenService.getUsernameFromToken(request);
        List<Message> messages = messageService.memberSelectMessage(findMember.getId());

        return messages.stream().map(message -> new MemberMessageResDTO(message.getId(), message.getMessage())).collect(Collectors.toList());

    }

    @GetMapping("/answer")
    public List<MemberAnswerResDTO> memberSelectAnswer(HttpServletRequest request){

        Member usernameFromToken = tokenService.getUsernameFromToken(request);

        List<Message> findByMessage = messageService.findByWithAnswer(usernameFromToken.getId());

     return  findByMessage.stream().map(f -> new MemberAnswerResDTO(f.getId(), f.getAnswer().getAnswer())).collect(Collectors.toList());
    }









}
