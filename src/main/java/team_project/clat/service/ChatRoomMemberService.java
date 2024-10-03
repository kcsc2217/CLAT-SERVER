package team_project.clat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.ChatRoom;
import team_project.clat.domain.ChatRoomMember;
import team_project.clat.domain.Member;
import team_project.clat.dto.request.RoomKeyReqDTO;
import team_project.clat.exception.DuplicateException;
import team_project.clat.exception.NotFoundException;
import team_project.clat.repository.ChatRoomMemberRepository;
import team_project.clat.repository.ChatRoomRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatRoomMemberService {

    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final ChatRoomRepository chatRoomRepository;



    public void saveChatRoomMember(RoomKeyReqDTO roomKeyReq, Member member) {

        ChatRoom findBychatRoom = chatRoomRepository.findById(roomKeyReq.getChatRoomId()).orElseThrow(() -> new NotFoundException("해당 채팅방은 없습니다"));
        boolean flag = chatRoomMemberRepository.existsByChatRoomIdAndMemberId(findBychatRoom.getId(), member.getId());

        if(flag) {
            throw new DuplicateException("해당 멤버는 이미 입장하여 입장번호 생략가능합니다");
        }

        chatRoomMemberRepository.save(new ChatRoomMember(true, findBychatRoom, member));

    }
}
