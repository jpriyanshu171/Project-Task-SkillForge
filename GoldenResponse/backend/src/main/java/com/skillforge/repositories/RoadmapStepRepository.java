package com.skillforge.repositories;

import com.skillforge.entities.RoadmapStepEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadmapStepRepository extends JpaRepository<RoadmapStepEntity, Long> {
    List<RoadmapStepEntity> findByRoadmapIdOrderByStepOrderAsc(Long roadmapId);
}
