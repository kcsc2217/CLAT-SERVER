package team_project.clat.repository.querydsl;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import team_project.clat.domain.Message;
import team_project.clat.dto.response.PageNationMessageResDTO;

public interface MessageRepositoryCustom {

    Slice<PageNationMessageResDTO> findSliceByMessageAndChatRoom(Long chatRoomId, Pageable pageable);
}
