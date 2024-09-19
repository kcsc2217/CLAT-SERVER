package team_project.clat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team_project.clat.domain.Like;
import team_project.clat.dto.LikeRequestDTO;
import team_project.clat.dto.LikeResponseDTO;
import team_project.clat.repository.LikeRepository;
import team_project.clat.service.LikeService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController{

    private final LikeService likeService;
    private final LikeRepository likeRepository;



    @PostMapping()
    public LikeResponseDTO saveLike(@RequestBody LikeRequestDTO likeRequestDTO){
        likeService.like(likeRequestDTO.getMessageId(), likeRequestDTO.getEmoticon());

        List<Like> findLikeLists = likeRepository.findByMessageId(likeRequestDTO.getMessageId());

        return new LikeResponseDTO(findLikeLists);

    }

}
