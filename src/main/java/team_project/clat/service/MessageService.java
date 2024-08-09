package team_project.clat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.ChatRoom;
import team_project.clat.domain.Member;
import team_project.clat.domain.Message;
import team_project.clat.repo.ChatRoomRepository;
import team_project.clat.repo.MemberRepository;
import team_project.clat.repo.MessageRepository;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MessageService {

    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;


    @Transactional
    public Long save(Message message) {
        return messageRepository.save(message).getId();
    }


    @Transactional
    public Long saveMessage(String senderName, Long chatRoomId, String message){ //controller 에서 해당 회원이 메세지를 사용할 수 있는지 검증
        ChatRoom findByChatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new IllegalArgumentException("해당 채팅방이 없습니다"));

        Message saveMessage = Message.createMessage(senderName, findByChatRoom, message);

        save(saveMessage);

        return saveMessage.getId();
    }







}
