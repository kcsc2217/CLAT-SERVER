package team_project.clat.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.ChatRoom;
import team_project.clat.domain.Message;
import team_project.clat.dto.response.ChatRoomMessageResDTO;
import team_project.clat.dto.request.RoomKeyReqDTO;
import team_project.clat.repository.ChatRoomRepository;

import java.util.List;
import java.util.Optional;


@SpringBootTest
@Transactional
class ChatRoomServiceTest {

    @Autowired
    private ChatRoomService chatRoomService;


    @Autowired
    private ChatRoomRepository chatRoomRepository;








    @Test
    public void fetchjoin_쿼리_보기() throws Exception {
       //given
        Optional<ChatRoom> fetchByCourseAndMessage = chatRoomRepository.findFetchByCourseAndMessage(1L);

        //when

        ChatRoom chatRoom = fetchByCourseAndMessage.get();

        System.out.println(chatRoom.getCourse().getRoom());

        List<Message> messageList = chatRoom.getMessageList();

        for(Message message : messageList){
            System.out.println(message.getMessage());
        }


        //then
    }

    @Test
    public void 방_입장() throws Exception {
       //given
        RoomKeyReqDTO roomKeyReq = new RoomKeyReqDTO(1L, 3489);

        //when

        boolean b = chatRoomService.validationRoom(roomKeyReq);

        //then

        Assertions.assertThat(b).isEqualTo(true);
    }

    @Test
    public void chatroom_에서_메서지와_이미지전체조회() throws Exception {
       //given
        ChatRoom chatRoom = chatRoomService.findFetchMessageAndImage(1L);
        //when

        ChatRoomMessageResDTO chatRoomMessageDTO = new ChatRoomMessageResDTO(chatRoom);
        //then
    }






}