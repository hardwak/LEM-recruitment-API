package com.lemwroclaw.LEM_recruitment_website.recruitment_module.application;

import com.lemwroclaw.LEM_recruitment_website.recruitment_module.application.dto.ApplicationCreationDTO;
import com.lemwroclaw.LEM_recruitment_website.recruitment_module.application.dto.ApplicationResponseDTO;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApplicationResponseDTO> createApplication(@RequestBody ApplicationCreationDTO applicationCreationDTO) {
        return applicationService.createApplication(applicationCreationDTO);
    }

    @GetMapping
    public ResponseEntity<List<ApplicationResponseDTO>> getApplications() {
        return applicationService.getApplications();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponseDTO> getApplication(@PathVariable Long id) {
        return applicationService.getApplicationById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
    }

}
