package com.skillforge.services.skills;

import com.skillforge.dto.request.SkillRequest;
import com.skillforge.dto.response.SkillAnalyticsResponse;
import com.skillforge.dto.response.SkillResponse;
import com.skillforge.entities.SkillEntity;
import com.skillforge.entities.UserEntity;
import com.skillforge.exceptions.ResourceNotFoundException;
import com.skillforge.repositories.SkillRepository;
import com.skillforge.repositories.UserRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    public SkillServiceImpl(SkillRepository skillRepository, UserRepository userRepository) {
        this.skillRepository = skillRepository;
        this.userRepository = userRepository;
    }

    @Override
    public SkillResponse createSkill(Long userId, SkillRequest request) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        SkillEntity skill = new SkillEntity();
        skill.setUser(user);
        skill.setName(request.getName());
        skill.setCategory(request.getCategory());
        skill.setProficiency(request.getProficiency());
        skill.setPriority(request.getPriority());
        skill.setProgress(request.getProgress());
        skillRepository.save(skill);

        return map(skill);
    }

    @Override
    public List<SkillResponse> listSkills(Long userId) {
        return skillRepository.findByUserId(userId).stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public Set<String> listCategories(Long userId) {
        return skillRepository.findByUserId(userId).stream().map(SkillEntity::getCategory).collect(Collectors.toSet());
    }

    @Override
    public SkillAnalyticsResponse getAnalytics(Long userId) {
        var skills = skillRepository.findByUserId(userId);
        long totalSkills = skills.size();
        double averageProgress = skills.stream().mapToInt(skill -> skill.getProgress() == null ? 0 : skill.getProgress()).average().orElse(0);
        var skillsByCategory = skills.stream().collect(Collectors.groupingBy(SkillEntity::getCategory, Collectors.counting()));
        return new SkillAnalyticsResponse(totalSkills, skillsByCategory, averageProgress);
    }

    @Override
    public SkillResponse updateSkill(Long userId, Long id, SkillRequest request) {
        SkillEntity skill = skillRepository.findById(id)
                .filter(s -> s.getUser().getId().equals(userId))
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));

        skill.setName(request.getName());
        skill.setCategory(request.getCategory());
        skill.setProficiency(request.getProficiency());
        skill.setPriority(request.getPriority());
        skill.setProgress(request.getProgress());
        skill.setUpdatedAt(java.time.Instant.now());

        return map(skill);
    }

    @Override
    public void deleteSkill(Long userId, Long id) {
        SkillEntity skill = skillRepository.findById(id)
                .filter(s -> s.getUser().getId().equals(userId))
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));
        skillRepository.delete(skill);
    }

    private SkillResponse map(SkillEntity entity) {
        return new SkillResponse(entity.getId(), entity.getName(), entity.getCategory(), entity.getProficiency(), entity.getPriority(), entity.getProgress());
    }
}
