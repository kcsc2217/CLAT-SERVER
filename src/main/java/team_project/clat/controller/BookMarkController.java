package team_project.clat.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import team_project.clat.domain.Member;
import team_project.clat.dto.response.BookMarkSaveDTO;
import team_project.clat.dto.response.MessageBookMarkDTO;
import team_project.clat.service.BookMarkService;
import team_project.clat.service.TokenService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookMark")
@Slf4j
public class BookMarkController {

    private final BookMarkService bookMarkService;

    private final TokenService tokenService;


    @PostMapping("/{messageId}")
    public BookMarkSaveDTO createBookMark(@PathVariable Long messageId, HttpServletRequest request) {
        Member findMember = tokenService.getUsernameFromToken(request);

        log.info("북마크 등록");
       return bookMarkService.markBook(messageId, findMember);
    }

    @GetMapping()
    public List<MessageBookMarkDTO> findAllBookMarks(HttpServletRequest request) {
        Member findMember = tokenService.getUsernameFromToken(request);
        log.info("북마크 메시지들 조회");

        return bookMarkService.findAll(findMember);

    }
}
