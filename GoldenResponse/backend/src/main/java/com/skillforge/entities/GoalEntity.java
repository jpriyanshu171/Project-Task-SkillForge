package com.skillforge.entities;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "goals", indexes = @Index(name = "idx_goals_user_id", columnList = "user_id"))
@Getter
@Setter
@NoArgsConstructor
public class GoalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private String title;
    @Column(length = 1000)
    private String description;
    private LocalDate deadline;
    private String priority;
    private String status;
    private Integer progress;
    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();
}
