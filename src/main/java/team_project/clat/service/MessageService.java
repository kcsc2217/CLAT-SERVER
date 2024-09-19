package team_project.clat.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.FlushModeType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.*;
import team_project.clat.domain.File.Image;
import team_project.clat.dto.FileImageDTO;
import team_project.clat.exception.DuplicateException;
import team_project.clat.exception.NotFoundException;
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
    private final MemoRepository memoRepository;



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
    public Message saveFileMessage(String username, Long chatRoomId, List<FileImageDTO> fileImageDTOList){
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


    public List<Message> memberSelectMessage(Long memberId){
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new NotFoundException("해당 학생이 없습니다"));

        return member.getMessageList();
    }


    @Transactional  //메모 추가 로직
    public Message saveMemo(Long messageId, String memo){
        Message message = messageRepository.findMessageByMemberId(messageId).orElseThrow(() -> new NotFoundException("해당 메세지는 없습니다")); //영속성 콘텐츠에 담음

        validationMemo(message);

        Memo saveMemo = memoRepository.save(new Memo(memo)); //단방향 매핑

        message.addMemo(saveMemo); // 이때 업데이트 문 나감

        return message;
    }

    private void validationMemo(Message message) {
        if(message.getMemo() != null){
            throw new DuplicateException("메모가 이미 존재합니다");
        }
    }

    private List<Image> convertToImages(List<FileImageDTO> fileImageDTOList) {
        List<Long> imagesId = fileImageDTOList.stream().map(FileImageDTO::getImageId).collect(Collectors.toList()); // 이미지

        List<Image> images = imageRepository.findByIdIn(imagesId); //이미지 아이디로 묶음 찾기

        for(Image image : images){
            log.info(image.getAccessUrl());
        }



        return images;

    }


    public Message findByWithMemo(Long messageId){
        return messageRepository.findMessageById(messageId).orElseThrow(()->  new NotFoundException("해당 메세지는 찾을 수 없습니다"));
    }



}
