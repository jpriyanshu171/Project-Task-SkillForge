package com.skillforge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SkillResponse {
    private final Long id;
    private final String name;
    private final String category;
    private final String proficiency;
    private final Integer priority;
    private final Integer progress;
}
