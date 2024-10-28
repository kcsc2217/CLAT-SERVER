package team_project.clat.repository.querydsl;

import team_project.clat.dto.response.LikeResDTO;

import java.util.List;
import java.util.Map;

public interface LikeRepositoryCustom {

    List<LikeResDTO> findByQslLikesCount(Long messageId);

    Map<Long, List<LikeResDTO>> findByQslLikes(List<Long> messageIds);
}
