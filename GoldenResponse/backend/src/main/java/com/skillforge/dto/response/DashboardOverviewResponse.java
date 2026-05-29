package com.skillforge.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DashboardOverviewResponse {
    private final Long totalSkills;
    private final Long totalGoals;
    private final Long totalRoadmaps;
    private final Long totalAssessments;
    private final List<RecommendationSummary> recommendations;
}
