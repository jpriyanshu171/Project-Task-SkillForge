package com.skillforge.entities;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "assessments", indexes = @Index(name = "idx_assessments_user_id", columnList = "user_id"))
@Getter
@Setter
@NoArgsConstructor
public class AssessmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private String skillName;
    private Integer score;
    private Integer totalQuestions;
    private String result;
    private Instant completedAt = Instant.now();
}
