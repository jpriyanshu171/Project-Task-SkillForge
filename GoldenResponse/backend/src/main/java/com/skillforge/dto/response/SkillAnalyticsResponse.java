package com.skillforge.dto.response;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SkillAnalyticsResponse {
    private final Long totalSkills;
    private final Map<String, Long> skillsByCategory;
    private final Double averageProgress;
}
