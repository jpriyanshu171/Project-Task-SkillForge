package com.skillforge.services.skills;

import com.skillforge.dto.request.SkillRequest;
import com.skillforge.dto.response.SkillResponse;
import java.util.List;

import com.skillforge.dto.response.SkillAnalyticsResponse;
import java.util.Set;

public interface SkillService {
    SkillResponse createSkill(Long userId, SkillRequest request);
    List<SkillResponse> listSkills(Long userId);
    SkillResponse updateSkill(Long userId, Long id, SkillRequest request);
    void deleteSkill(Long userId, Long id);
    Set<String> listCategories(Long userId);
    SkillAnalyticsResponse getAnalytics(Long userId);
}
