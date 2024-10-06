package team_project.clat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.Member;
import team_project.clat.domain.Memo;
import team_project.clat.domain.Message;
import team_project.clat.dto.request.MessageMemoReqDTO;
import team_project.clat.dto.response.MemoResDTO;
import team_project.clat.dto.response.MemoUpdateResDTO;
import team_project.clat.dto.response.MessageMemoResDTO;
import team_project.clat.exception.DuplicateException;
import team_project.clat.exception.MemberNotAccessException;
import team_project.clat.exception.NotFoundException;
import team_project.clat.exception.UnAuthorizationException;
import team_project.clat.repository.MemoRepository;
import team_project.clat.repository.MessageRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemoService {

    private final MessageRepository messageRepository;
    private final MemoRepository memoRepository;


    public MemoResDTO findByWithMemo(Long messageId, Member member){
        Message message = messageRepository.findMessageById(messageId).orElseThrow(() -> new NotFoundException("해당 메세지는 찾을 수 없습니다"));

        validationAccessMember(member, message);
        return new MemoResDTO(message);
    }

    @Transactional  //메모 추가 로직
    public MessageMemoResDTO saveMemo(MessageMemoReqDTO messageMemoRequestDTO, Member member){
        Message message = messageRepository.findMessageByMemberId(messageMemoRequestDTO.getMessageId()).orElseThrow(() -> new NotFoundException("해당 메세지는 없습니다")); //영속성 콘텐츠에 담음

        validationMemo(member, message); // 이미 메모가 있는지 검증과 해당 멤버의 메모만 접근가능

        Memo saveMemo = memoRepository.save(new Memo(messageMemoRequestDTO.getMemo())); //단방향 매핑

        message.addMemo(saveMemo); // 이때 업데이트 문 나감

        return new MessageMemoResDTO(message);
    }

    @Transactional
    public MemoUpdateResDTO updateMemo(Long messageId, String memoContent, Member member){
        Message message = messageRepository.findFetchMemoByMessageId(messageId).orElseThrow(() -> new NotFoundException("해당 메세지는 없습니다")); //영속성 콘텐츠에 담음

        validationAccessMember(member, message);

        Memo memo = message.getMemo();

        memo.updateMemo(memoContent);

        return new MemoUpdateResDTO(memo);

    }

    public List<MemoResDTO> findByWithChatRoomMemo(Long chatRoomId, Long memberId){
        List<Message> messages = messageRepository.findByChatRoomId(chatRoomId, memberId).orElseThrow(() -> new NotFoundException("해당 채팅방에 대한 메시지를 찾을수 없습니다"));

        return messages.stream().map(MemoResDTO::new).toList();
    }


    private void validationAccessMember(Member member, Message message) {
        if(message.getMember().getId() != member.getId()){
            throw new MemberNotAccessException("해당 멤버의 메모가 아니므로 접근 할 수 없습니다");
        }
    }

    private void validationMemo( Member member, Message message) {
        if(message.getMemo() != null){
            throw new DuplicateException("메모가 이미 존재합니다");
        }
        validationAccessMember(member, message);
    }
}
