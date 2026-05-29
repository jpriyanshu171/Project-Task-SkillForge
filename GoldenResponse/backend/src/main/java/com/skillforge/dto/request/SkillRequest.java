package com.skillforge.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SkillRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @NotBlank
    private String proficiency;

    @NotNull
    private Integer priority;

    @NotNull
    private Integer progress;
}
