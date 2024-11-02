package com.lemwroclaw.LEM_recruitment_website.recruitment_module.application;

import com.lemwroclaw.LEM_recruitment_website.recruitment_module.application.dto.ApplicationCreationDTO;
import com.lemwroclaw.LEM_recruitment_website.recruitment_module.application.dto.ApplicationResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;

    public ApplicationService(ApplicationRepository applicationRepository, ApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
    }

    public ResponseEntity<ApplicationResponseDTO> createApplication(ApplicationCreationDTO dto) {
        Application application = applicationMapper.toApplication(dto);
        applicationRepository.save(application);

        ApplicationResponseDTO dtoResponse = applicationMapper.toApplicationResponseDTO(application);

        return new ResponseEntity<>(dtoResponse, HttpStatus.CREATED);
    }

    public ResponseEntity<ApplicationResponseDTO> getApplicationById(Long id) {
        var application = applicationRepository.findById(id).orElse(null);
        if (application == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(applicationMapper.toApplicationResponseDTO(application), HttpStatus.OK);
    }

    public ResponseEntity<List<ApplicationResponseDTO>> getApplications() {
        List<ApplicationResponseDTO> applications = applicationRepository
                .findAll()
                .stream()
                .map(applicationMapper::toApplicationResponseDTO)
                .toList();
        if (applications.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    //better way to return responseEntity accepted???
    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }


}
