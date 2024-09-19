package team_project.clat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.Enum.Emoticon;
import team_project.clat.domain.Like;
import team_project.clat.domain.Message;
import team_project.clat.exception.NotFoundException;
import team_project.clat.repository.LikeRepository;
import team_project.clat.repository.MessageRepository;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {

    private final LikeRepository likeRepository;
    private final MessageRepository messageRepository;


    @Transactional
    public Long like(Long messageId, Emoticon emoticon) {
        Message message = messageRepository.findById(messageId).orElseThrow(() -> new NotFoundException("해당 메세지는 존재하지 않습니다"));

        Like save = likeRepository.save(new Like(message, emoticon));

        return save.getId();
    }


}
