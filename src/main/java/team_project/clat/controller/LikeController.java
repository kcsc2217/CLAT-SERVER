package team_project.clat.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import team_project.clat.domain.Member;
import team_project.clat.dto.request.LikeReqDTO;
import team_project.clat.dto.response.LikeResDTO;
import team_project.clat.service.LikeService;
import team_project.clat.service.TokenService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController{

    private final LikeService likeService;

    private final TokenService tokenService;

    @PostMapping()
    public List<LikeResDTO> saveLike(@RequestBody LikeReqDTO likeRequestDTO, HttpServletRequest request){

        log.info("이모티콘");

        Member findByMember = tokenService.getUsernameFromToken(request);  //멤버 찾음
        likeService.like(findByMember,likeRequestDTO.getMessageId(), likeRequestDTO.getEmoticon());

        return likeService.getLikes(likeRequestDTO.getMessageId());
    }

    @GetMapping("/{messageId}")
    public List<LikeResDTO> getLike(@PathVariable Long messageId){

        return likeService.getLikes(messageId);
    }


}
