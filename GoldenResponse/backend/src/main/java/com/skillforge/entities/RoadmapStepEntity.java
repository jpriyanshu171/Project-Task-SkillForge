package com.skillforge.entities;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roadmap_steps", indexes = @Index(name = "idx_roadmap_steps_roadmap_id", columnList = "roadmap_id"))
@Getter
@Setter
@NoArgsConstructor
public class RoadmapStepEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roadmap_id", nullable = false)
    private RoadmapEntity roadmap;

    private String title;
    @Column(length = 1000)
    private String description;
    private boolean completed;
    private Integer stepOrder;
    private Instant createdAt = Instant.now();
}
