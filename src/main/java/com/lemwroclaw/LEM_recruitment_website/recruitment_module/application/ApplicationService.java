package com.lemwroclaw.LEM_recruitment_website.recruitment_module.application;

import com.lemwroclaw.LEM_recruitment_website.auth.AuthenticationService;
import com.lemwroclaw.LEM_recruitment_website.recruitment_module.application.dto.ApplicationCreationDTO;
import com.lemwroclaw.LEM_recruitment_website.recruitment_module.application.dto.ApplicationResponseDTO;
import com.lemwroclaw.LEM_recruitment_website.recruitment_module.recruitment.RecruitmentRepository;
import com.lemwroclaw.LEM_recruitment_website.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final UserRepository userRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final AuthenticationService authenticationService;

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

    public ResponseEntity<List<ApplicationResponseDTO>> getApplications(Long userId, Long recruitmentId) {
        var applications = applicationMapper.toApplicationResponseDTOList(
                applicationRepository.findApplicationsByUserIdAndRecruitmentId(recruitmentId, userId)
        );

        if (applications.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(applications);
    }

    //better way to return responseEntity accepted???
    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    public ResponseEntity<List<ApplicationResponseDTO>> getApplicationsOfCurrentUser() {
        var applications = applicationMapper.toApplicationResponseDTOList(
                applicationRepository.findAllByOwnerEmail(authenticationService.getAuthenticatedUserEmail())
        );

        if (applications.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(applications);
    }

    public ResponseEntity<ApplicationResponseDTO> updateApplication(ApplicationCreationDTO dto, Long id) {
        var applicationToUpdate = applicationRepository.getReferenceById(id);

        applicationToUpdate.setOwner(
                userRepository.getReferenceById(dto.userId())
        );
        applicationToUpdate.setRecruitment(
                recruitmentRepository.getReferenceById(dto.recruitmentId())
        );
        applicationToUpdate.setAnswers(dto.answers());

        applicationRepository.save(applicationToUpdate);

        return new ResponseEntity<>(applicationMapper.toApplicationResponseDTO(applicationToUpdate), HttpStatus.CREATED);
    }

//    public ResponseEntity<List<ApplicationResponseDTO>> getAllApplications() {
//        var applications = applicationMapper.toApplicationResponseDTOList(
//                applicationRepository.findAll()
//        );
//
//        if (applications.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(applications, HttpStatus.OK);
//    }
//    public ResponseEntity<List<ApplicationResponseDTO>> getApplicationsOfSpecificUser(Long userId) {
//        var applications = applicationMapper.toApplicationResponseDTOList(
//                applicationRepository.findAllByOwnerId(userId)
//        );
//
//        if (applications.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return ResponseEntity.ok(applications);
//    }
//
//    public ResponseEntity<List<ApplicationResponseDTO>> getApplicationsOfSpecificRecruitment(Long recruitmentId) {
//        var applications = applicationMapper.toApplicationResponseDTOList(
//                applicationRepository.findAllByRecruitmentId(recruitmentId)
//        );
//
//        if (applications.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return ResponseEntity.ok(applications);
//    }
//
//    public ResponseEntity<List<ApplicationResponseDTO>> getApplicationsOfSpecificRecruitmentAndUser(Long recruitmentId, Long userId) {
//        var applications = applicationMapper.toApplicationResponseDTOList(
//                applicationRepository.findApplicationsByUserIdAndRecruitmentId(recruitmentId, userId)
//        );
//
//        if (applications.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return ResponseEntity.ok(applications);
//    }



}
