package team_project.clat.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import team_project.clat.dto.response.LikeResDTO;
import team_project.clat.dto.response.QLikeResDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static team_project.clat.domain.QLike.like;

public class LikeRepositoryImpl implements LikeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public LikeRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<LikeResDTO> findByQslLikesCount(Long messageId) {

        return queryFactory
                .select(new QLikeResDTO(like.emoticon, like.count(),like.message.id))
                .from(like)
                .where(like.message.id.eq(messageId))
                .groupBy(like.emoticon)
                .fetch();


    }

    @Override
    public Map<Long, List<LikeResDTO>> findByQslLikes(List<Long> messageIds) {
        List<LikeResDTO> likes = queryFactory
                .select(new QLikeResDTO(like.emoticon, like.count(),like.message.id))
                .from(like)
                .where(like.message.id.in(messageIds))  // 여러 messageId에 대해 조회
                .groupBy(like.message.id, like.emoticon)
                .fetch();

        // 각 messageId별로 LikeResDTO 리스트를 그룹화하여 반환
        return likes.stream()
                .collect(Collectors.groupingBy(LikeResDTO::getMessageId));
    }


}
