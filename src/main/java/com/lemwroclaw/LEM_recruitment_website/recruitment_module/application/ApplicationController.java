package com.lemwroclaw.LEM_recruitment_website.recruitment_module.application;

import com.lemwroclaw.LEM_recruitment_website.recruitment_module.application.dto.ApplicationCreationDTO;
import com.lemwroclaw.LEM_recruitment_website.recruitment_module.application.dto.ApplicationResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<ApplicationResponseDTO> createApplication(@Valid @RequestBody ApplicationCreationDTO applicationCreationDTO) {
        return applicationService.createApplication(applicationCreationDTO);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<List<ApplicationResponseDTO>> getApplications(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long recruitmentId
    ) {
        return applicationService.getApplications(userId, recruitmentId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<ApplicationResponseDTO> getApplication(@PathVariable Long id) {
        return applicationService.getApplicationById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public void deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<ApplicationResponseDTO> updateApplication(
            @Valid @RequestBody ApplicationCreationDTO applicationCreationDTO,
            @PathVariable Long id
    ) {
        return applicationService.updateApplication(applicationCreationDTO, id);
    }

    @GetMapping("/my-applications")
    public ResponseEntity<List<ApplicationResponseDTO>> getUserApplications() {
        return applicationService.getApplicationsOfCurrentUser();
    }

}
