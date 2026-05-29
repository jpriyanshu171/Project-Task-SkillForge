package com.skillforge.repositories;

import com.skillforge.entities.RoadmapEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadmapRepository extends JpaRepository<RoadmapEntity, Long> {
    List<RoadmapEntity> findByUserId(Long userId);
}
