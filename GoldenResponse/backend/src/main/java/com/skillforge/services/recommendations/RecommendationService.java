package com.skillforge.services.recommendations;

import com.skillforge.dto.response.RecommendationSummary;
import java.util.List;

public interface RecommendationService {
    List<RecommendationSummary> listRecommendations(Long userId);
    List<RecommendationSummary> generateRecommendations(Long userId);
}
