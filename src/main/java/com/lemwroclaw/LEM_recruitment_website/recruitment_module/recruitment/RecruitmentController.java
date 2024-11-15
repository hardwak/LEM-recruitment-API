package com.lemwroclaw.LEM_recruitment_website.recruitment_module.recruitment;

import com.lemwroclaw.LEM_recruitment_website.recruitment_module.recruitment.dto.RecruitmentCreationDTO;
import com.lemwroclaw.LEM_recruitment_website.recruitment_module.recruitment.dto.RecruitmentResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/recruitments")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    public RecruitmentController(RecruitmentService recruitmentService) {
        this.recruitmentService = recruitmentService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<RecruitmentCreationDTO> createRecruitment(@Valid @RequestBody RecruitmentCreationDTO dto) {
        return recruitmentService.createRecruitment(dto);
    }

    @GetMapping
    public ResponseEntity<List<RecruitmentResponseDTO>> getAllRecruitments() {
        return recruitmentService.getAllRecruitments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecruitmentResponseDTO> getRecruitmentById(@PathVariable Long id) {
        return recruitmentService.getRecruitmentById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public void deleteRecruitment(@PathVariable Long id) {
        recruitmentService.deleteRecruitmentById(id);
    }
}
