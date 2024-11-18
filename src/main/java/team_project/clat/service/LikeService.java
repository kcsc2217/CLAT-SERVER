package team_project.clat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.Enum.Emoticon;
import team_project.clat.domain.Like;
import team_project.clat.domain.Member;
import team_project.clat.domain.Message;
import team_project.clat.dto.response.LikeResDTO;
import team_project.clat.exception.DuplicateException;
import team_project.clat.exception.NotFoundException;
import team_project.clat.repository.LikeRepository;
import team_project.clat.repository.MessageRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {

    private final LikeRepository likeRepository;
    private final MessageRepository messageRepository;


    @Transactional
    public Long like(Member member, Long messageId, Emoticon emoticon) {

        if(likeRepository.existsByMessageIdAndMemberId(messageId, member.getId())) {
            throw new DuplicateException("해당 회원의 이모티콘 이미 존재합니다");
        }
        Message message = messageRepository.findById(messageId).orElseThrow(() -> new NotFoundException("해당 메세지는 존재하지 않습니다"));

        Like save = likeRepository.save(new Like(member,message, emoticon));

        return save.getId();
    }


    public List<LikeResDTO> getLikes(Long messageId){

        return likeRepository.findByQslLikesCount(messageId);

    }
/*
    private List<LikeResDTO> getLikemessagaeIdList(Long messageId) {
        List<Object[]> byMessageId = likeRepository.findByMessageId(messageId);

        List<LikeResDTO> likeResDTOS = new ArrayList<>();

        for(Object[] obj : byMessageId) {  //이모티콘 꺼내
            Emoticon emoticon = (Emoticon) obj[0];
             Long count = (Long) obj[1];

            likeResDTOS.add(new LikeResDTO(emoticon, count));
        }

        return  likeResDTOS;
    }

 */

}
