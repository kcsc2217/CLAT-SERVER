package team_project.clat.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import team_project.clat.domain.Member;
import team_project.clat.dto.response.MessageBookMarkResDTO;
import team_project.clat.service.BookMarkService;
import team_project.clat.service.TokenService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookMark")
@Slf4j
public class BookMarkController {

    private final BookMarkService bookMarkService;

    private final TokenService tokenService;


    @PostMapping("/{messageId}")
    public MessageBookMarkResDTO createBookMark(@PathVariable Long messageId, HttpServletRequest request) {
        Member findMember = tokenService.getUsernameFromToken(request);

       return bookMarkService.markBook(messageId, findMember);
    }

}
