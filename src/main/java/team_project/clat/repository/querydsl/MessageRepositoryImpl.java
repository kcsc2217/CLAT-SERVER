package team_project.clat.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import team_project.clat.domain.Message;
import team_project.clat.domain.QAnswer;
import team_project.clat.domain.QMessage;
import team_project.clat.dto.response.PageNationMessageResDTO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static team_project.clat.domain.QMember.member;
import static team_project.clat.domain.QMessage.message1;


public class MessageRepositoryImpl implements MessageRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public MessageRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Slice<PageNationMessageResDTO> findSliceByMessageAndChatRoom(Long chatRoomId, Pageable pageable) {

        QMessage message = message1;
        QAnswer answer = QAnswer.answer1;

        List<Message> result = queryFactory
                .selectFrom(message)
                .join(message.member, member).fetchJoin()
                .leftJoin(message.answer, answer).fetchJoin()
                .where(message.chatRoom.id.eq(chatRoomId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .orderBy(message.createdDate.desc())
                .fetch();


        List<PageNationMessageResDTO> list = result.stream().map(PageNationMessageResDTO::new).collect(Collectors.toList());


        boolean hasNext = false;

        if(result.size() > pageable.getPageSize()){
            hasNext = true;
            list.remove(pageable.getPageSize());
        }

        Collections.reverse(list);


        return new SliceImpl<>(list,pageable,hasNext);


    }
}
