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
import team_project.clat.dto.CourseHomeDTO;
import team_project.clat.dto.MemberAnswerDTO;
import team_project.clat.dto.MemberMessageDTO;
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
    public List<CourseHomeDTO> selectCourse(HttpServletRequest request, @RequestParam("term") String term){

        log.info("해당 멤버의 수업 조회");

        Member findMember = tokenService.getUsernameFromToken(request);

        List<Course> courseList = studentCourseService.courseList(findMember.getId(), term);

       return courseList.stream().map(CourseHomeDTO::new).collect(Collectors.toList());
    }


    @GetMapping("/messages")
    public List<MemberMessageDTO> memberSelectMessage(HttpServletRequest request){
        Member findMember = tokenService.getUsernameFromToken(request);
        List<Message> messages = messageService.memberSelectMessage(findMember.getId());

        return messages.stream().map(message -> new MemberMessageDTO(message.getId(), message.getMessage())).collect(Collectors.toList());

    }

    @GetMapping("/answer")
    public List<MemberAnswerDTO> memberSelectAnswer(HttpServletRequest request){

        Member usernameFromToken = tokenService.getUsernameFromToken(request);

        List<Message> findByMessage = messageService.findByWithAnswer(usernameFromToken.getId());

     return  findByMessage.stream().map(f -> new MemberAnswerDTO(f.getId(), f.getAnswer().getAnswer())).collect(Collectors.toList());
    }









}
