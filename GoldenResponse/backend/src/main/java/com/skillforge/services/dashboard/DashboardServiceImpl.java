package com.skillforge.services.dashboard;

import com.skillforge.dto.response.DashboardOverviewResponse;
import com.skillforge.dto.response.RecommendationSummary;
import com.skillforge.repositories.AssessmentRepository;
import com.skillforge.repositories.GoalRepository;
import com.skillforge.repositories.RoadmapRepository;
import com.skillforge.repositories.SkillRepository;
import com.skillforge.repositories.RecommendationRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final SkillRepository skillRepository;
    private final GoalRepository goalRepository;
    private final RoadmapRepository roadmapRepository;
    private final AssessmentRepository assessmentRepository;
    private final RecommendationRepository recommendationRepository;

    public DashboardServiceImpl(SkillRepository skillRepository, GoalRepository goalRepository,
                                RoadmapRepository roadmapRepository, AssessmentRepository assessmentRepository,
                                RecommendationRepository recommendationRepository) {
        this.skillRepository = skillRepository;
        this.goalRepository = goalRepository;
        this.roadmapRepository = roadmapRepository;
        this.assessmentRepository = assessmentRepository;
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public DashboardOverviewResponse getOverview(Long userId) {
        Long skills = (long) skillRepository.findByUserId(userId).size();
        Long goals = (long) goalRepository.findByUserId(userId).size();
        Long roadmaps = (long) roadmapRepository.findByUserId(userId).size();
        Long assessments = (long) assessmentRepository.findByUserId(userId).size();
        List<RecommendationSummary> recommendations = recommendationRepository.findByUserId(userId).stream()
                .map(entity -> new RecommendationSummary(entity.getTitle(), entity.getType(), entity.getReason()))
                .toList();
        return new DashboardOverviewResponse(skills, goals, roadmaps, assessments, recommendations);
    }
}
