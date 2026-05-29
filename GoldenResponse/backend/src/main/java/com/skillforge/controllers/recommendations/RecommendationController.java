package com.skillforge.controllers.recommendations;

import com.skillforge.dto.response.ApiResponse;
import com.skillforge.dto.response.RecommendationSummary;
import com.skillforge.services.recommendations.RecommendationService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {
    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RecommendationSummary>>> getRecommendations(@AuthenticationPrincipal UserDetails userDetails) {
        Long userId = Long.parseLong(userDetails.getUsername());
        return ResponseEntity.ok(new ApiResponse<>(true, "Recommendations retrieved", recommendationService.listRecommendations(userId)));
    }

    @PostMapping("/generate")
    public ResponseEntity<ApiResponse<List<RecommendationSummary>>> generateRecommendations(@AuthenticationPrincipal UserDetails userDetails) {
        Long userId = Long.parseLong(userDetails.getUsername());
        return ResponseEntity.ok(new ApiResponse<>(true, "Recommendations generated", recommendationService.generateRecommendations(userId)));
    }
}
