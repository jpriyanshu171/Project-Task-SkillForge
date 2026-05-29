package com.skillforge.entities;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recommendations", indexes = @Index(name = "idx_recommendations_user_id", columnList = "user_id"))
@Getter
@Setter
@NoArgsConstructor
public class RecommendationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private String type;
    private String title;
    private String url;
    @Column(length = 1000)
    private String reason;
    private Instant createdAt = Instant.now();
}
