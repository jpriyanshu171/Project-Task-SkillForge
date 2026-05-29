package com.skillforge.repositories;

import com.skillforge.entities.NotificationEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity> findByUserIdAndIsReadFalse(Long userId);
    List<NotificationEntity> findByUserId(Long userId);
}
