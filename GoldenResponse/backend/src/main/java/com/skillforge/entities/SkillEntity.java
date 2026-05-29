package com.skillforge.entities;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "skills", indexes = @Index(name = "idx_skills_user_id", columnList = "user_id"))
@Getter
@Setter
@NoArgsConstructor
public class SkillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private String name;
    private String category;
    private String proficiency;
    private Integer priority;
    private Integer progress;
    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();
}
