package com.skillforge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecommendationSummary {
    private final String title;
    private final String type;
    private final String reason;
}
