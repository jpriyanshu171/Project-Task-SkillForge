package com.skillforge.controllers.skills;

import com.skillforge.dto.request.SkillRequest;
import com.skillforge.dto.response.ApiResponse;
import com.skillforge.dto.response.SkillResponse;
import com.skillforge.services.skills.SkillService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SkillResponse>>> getSkills(@AuthenticationPrincipal UserDetails principal) {
        Long userId = Long.parseLong(principal.getUsername());
        return ResponseEntity.ok(new ApiResponse<>(true, "Skills retrieved", skillService.listSkills(userId)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SkillResponse>> createSkill(@AuthenticationPrincipal UserDetails principal,
            @Valid @RequestBody SkillRequest request) {
        Long userId = Long.parseLong(principal.getUsername());
        return ResponseEntity.ok(new ApiResponse<>(true, "Skill created", skillService.createSkill(userId, request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SkillResponse>> updateSkill(@AuthenticationPrincipal UserDetails principal,
            @PathVariable Long id, @Valid @RequestBody SkillRequest request) {
        Long userId = Long.parseLong(principal.getUsername());
        return ResponseEntity.ok(new ApiResponse<>(true, "Skill updated", skillService.updateSkill(userId, id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSkill(@AuthenticationPrincipal UserDetails principal,
            @PathVariable Long id) {
        Long userId = Long.parseLong(principal.getUsername());
        skillService.deleteSkill(userId, id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Skill deleted", null));
    }

    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<java.util.Set<String>>> categories(@AuthenticationPrincipal UserDetails principal) {
        Long userId = Long.parseLong(principal.getUsername());
        return ResponseEntity.ok(new ApiResponse<>(true, "Categories retrieved", skillService.listCategories(userId)));
    }

    @GetMapping("/analytics")
    public ResponseEntity<ApiResponse<com.skillforge.dto.response.SkillAnalyticsResponse>> analytics(@AuthenticationPrincipal UserDetails principal) {
        Long userId = Long.parseLong(principal.getUsername());
        return ResponseEntity.ok(new ApiResponse<>(true, "Analytics retrieved", skillService.getAnalytics(userId)));
    }
}
