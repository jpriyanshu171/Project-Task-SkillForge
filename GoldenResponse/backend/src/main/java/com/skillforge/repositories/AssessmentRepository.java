package com.skillforge.repositories;

import com.skillforge.entities.AssessmentEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentRepository extends JpaRepository<AssessmentEntity, Long> {
    List<AssessmentEntity> findByUserId(Long userId);
}
