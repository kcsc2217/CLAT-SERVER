package team_project.clat.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.ChatRoom;
import team_project.clat.domain.Image;
import team_project.clat.domain.Member;
import team_project.clat.domain.Memo;
import team_project.clat.domain.Message;
import team_project.clat.dto.response.*;
import team_project.clat.dto.request.MessageMemoReqDTO;
import team_project.clat.exception.DuplicateException;
import team_project.clat.exception.MemberNotAccessException;
import team_project.clat.exception.NotFoundException;
import team_project.clat.exception.UnAuthorizationException;
import team_project.clat.repository.*;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MessageService {

    private final MessageRepository messageRepository;
    private final ImageRepository imageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;
    private final EntityManager em;



    @Transactional
    public Long save(Message message) {
        return messageRepository.save(message).getId();
    }


    @Transactional
    public Message saveMessage(String username, Long chatRoomId, String message){ //controller 에서 해당 회원이 메세지를 사용할 수 있는지 검증
        ChatRoom findByChatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new IllegalArgumentException("해당 채팅방이 없습니다"));
//        Course findCourse = courseRepository.findFetchCourseById(courseId).orElseThrow(() -> new IllegalArgumentException("해당 강의는 없습니다"));
        Member member = memberRepository.findByUsername(username);
        log.info("찾는로그");
        Message saveMessage = Message.createMessage(member, findByChatRoom, message);
        log.info("메세지 생성완료");

        Long saveId = save(saveMessage);

        return messageRepository.findById(saveId).orElseThrow(()-> new NotFoundException("해당 메세지는 없습니다"));
    }

    @Transactional
    public Message saveFileMessage(String username, Long chatRoomId, List<FileImageResDTO> fileImageDTOList){
        ChatRoom findByChatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new IllegalArgumentException("해당 채팅방이 없습니다"));
        log.info("메세지 찾기");

        Member member = memberRepository.findByUsername(username);

        List<Image> findByImage = convertToImages(fileImageDTOList); // 이미지 찾아옴
        Message saveMessage = Message.creteFilePathMessage(member, findByChatRoom, findByImage);
        log.info("메세지 생성완료");
        Long saveId = save(saveMessage);
        em.clear(); // 쿼리 성능 최적화 변경감지로 인한 업데이트를 없애기 위해 clear 작업 그러면 트랜잭션이 끝나서 변경감질할 일 없음

        List<Long> imageIds = findByImage.stream().map(Image::getId).collect(Collectors.toList()); //그러면 여기서 외래키 설정해주
        imageRepository.updateImageMessageId(saveId, imageIds);


        return saveMessage;

    }


    public List<MemberMessageResDTO> memberSelectMessage(Long memberId){
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new NotFoundException("해당 학생이 없습니다"));

        List<Message> messageList = member.getMessageList();

        return messageList.stream().map(MemberMessageResDTO::new).toList();

    }


    // 서브 쿼리를 사용한 조회
    public List<Message> findSubQueryFetchMessageAndImage(Long chatRoomId){
        return messageRepository.findSubFetchJoinByMessage(chatRoomId).orElseThrow(()-> new NotFoundException("해당 채팅방에 메시지는 찾을 수 없습니다"));
    }


    // 채팅방 전체 메시지 페치조인 쿼리 개선
    public ChatRoomMessageResDTO findUpgradeQueryByFetchMessageAndImage(Long chatRoomId){
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new NotFoundException("채팅방을 찾을 수 없습니다"));

        ChatRoomMessageResDTO chatRoomMessageDTO = new ChatRoomMessageResDTO(chatRoom.getRoomName());

        List<Message> messages = messageRepository.findQueryUpdateByChatRoomId(chatRoomId).orElseThrow(() -> new NotFoundException("채팅방을 찾을 수 없습니다"));

        chatRoomMessageDTO.setMessageFileResponseDTOS(messages.stream().map(MessageFileResDTO::new).collect(Collectors.toList()));

        return chatRoomMessageDTO;
    }

    public List<MemberAnswerResDTO> findByWithAnswer(Long memberId){
        List<Message> messages = messageRepository.findMessageByUsername(memberId).orElseThrow(() -> new NotFoundException("해당 멤버의 메시지를 찾을 수 없습니다"));

        return messages.stream().map(MemberAnswerResDTO::new).toList();
    }



    private List<Image> convertToImages(List<FileImageResDTO> fileImageDTOList) {
        List<Long> imagesId = fileImageDTOList.stream().map(FileImageResDTO::getImageId).collect(Collectors.toList()); // 이미지

        List<Image> images = imageRepository.findByIdIn(imagesId); //이미지 아이디로 묶음 찾기

        for(Image image : images){
            log.info(image.getAccessUrl());
        }



        return images;

    }

    private void validationAccessMember(Member member, Message message) {
        if(message.getMember().getId() != member.getId()){
            throw new MemberNotAccessException("해당 멤버의 메모가 아니므로 접근 할 수 없습니다");
        }
    }



}
