package team_project.clat.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team_project.clat.domain.File.Image;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByIdIn(List<Long> ids);

//    @Modifying
//    @Transactional
//    @Query("UPDATE Image i SET i.message.id = :messageId WHERE i.imageId IN :imageIds")
//    void updateImageMessageId(@Param("messageId") Long messageId, @Param("imageIds") List<Long> imageIds);



}
