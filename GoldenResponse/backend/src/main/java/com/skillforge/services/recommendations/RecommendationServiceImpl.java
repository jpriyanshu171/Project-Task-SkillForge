package com.skillforge.services.recommendations;

import com.skillforge.dto.response.RecommendationSummary;
import com.skillforge.entities.AssessmentEntity;
import com.skillforge.entities.GoalEntity;
import com.skillforge.entities.SkillEntity;
import com.skillforge.entities.RecommendationEntity;
import com.skillforge.entities.UserEntity;
import com.skillforge.repositories.AssessmentRepository;
import com.skillforge.repositories.GoalRepository;
import com.skillforge.repositories.RecommendationRepository;
import com.skillforge.repositories.SkillRepository;
import com.skillforge.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final SkillRepository skillRepository;
    private final AssessmentRepository assessmentRepository;
    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    public RecommendationServiceImpl(RecommendationRepository recommendationRepository,
                                     SkillRepository skillRepository,
                                     AssessmentRepository assessmentRepository,
                                     GoalRepository goalRepository,
                                     UserRepository userRepository) {
        this.recommendationRepository = recommendationRepository;
        this.skillRepository = skillRepository;
        this.assessmentRepository = assessmentRepository;
        this.goalRepository = goalRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<RecommendationSummary> listRecommendations(Long userId) {
        return recommendationRepository.findByUserId(userId).stream()
                .map(entity -> new RecommendationSummary(entity.getTitle(), entity.getType(), entity.getReason()))
                .toList();
    }

    @Override
    public List<RecommendationSummary> generateRecommendations(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow();
        List<RecommendationSummary> recommendations = new ArrayList<>();
        List<SkillEntity> skills = skillRepository.findByUserId(userId);
        List<AssessmentEntity> assessments = assessmentRepository.findByUserId(userId);
        List<GoalEntity> goals = goalRepository.findByUserId(userId);

        skills.stream()
                .filter(skill -> skill.getProgress() != null && skill.getProgress() < 60)
                .sorted(Comparator.comparing(SkillEntity::getProgress))
                .limit(3)
                .forEach(skill -> recommendations.add(new RecommendationSummary(
                        "Strengthen " + skill.getName(),
                        "Course",
                        "Low progress on " + skill.getName() + ". Consider guided projects and tutorials.")));

        assessments.stream()
                .filter(assessment -> assessment.getScore() != null && assessment.getScore() < 75)
                .limit(2)
                .forEach(assessment -> recommendations.add(new RecommendationSummary(
                        "Review " + assessment.getSkillName(),
                        "Practice",
                        "Assessment score indicates practice needed for " + assessment.getSkillName() + ".")));

        goals.stream()
                .filter(goal -> goal.getProgress() != null && goal.getProgress() < 50)
                .limit(2)
                .forEach(goal -> recommendations.add(new RecommendationSummary(
                        "Refocus goal: " + goal.getTitle(),
                        "Goal",
                        "Goal progress is behind schedule. Update your roadmap or break it into smaller steps.")));

        recommendations.add(new RecommendationSummary(
                "Explore certifications",
                "Certification",
                "Based on your skills and assessments, consider targeted certification paths."));

        RecommendationEntity entity = new RecommendationEntity();
        entity.setUser(user);
        entity.setType("Personalized");
        entity.setTitle("Continue your growth journey");
        entity.setUrl("https://learning.skillforge.example.com");
        entity.setReason("Recent activity suggests a strong learning momentum.");
        recommendationRepository.save(entity);

        return recommendations;
    }
}
