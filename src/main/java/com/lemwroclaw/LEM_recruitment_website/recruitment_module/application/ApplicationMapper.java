package com.lemwroclaw.LEM_recruitment_website.recruitment_module.application;

import com.lemwroclaw.LEM_recruitment_website.recruitment_module.application.dto.ApplicationCreationDTO;
import com.lemwroclaw.LEM_recruitment_website.recruitment_module.application.dto.ApplicationResponseDTO;
import com.lemwroclaw.LEM_recruitment_website.recruitment_module.recruitment.RecruitmentRepository;
import com.lemwroclaw.LEM_recruitment_website.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationMapper {

    private final UserRepository userRepository;
    private final RecruitmentRepository recruitmentRepository;

    public ApplicationMapper(UserRepository userRepository, RecruitmentRepository recruitmentRepository) {
        this.userRepository = userRepository;
        this.recruitmentRepository = recruitmentRepository;
    }

    public Application toApplication(ApplicationCreationDTO dto) {
        return Application.builder()
                .owner(userRepository.findById(dto.userId()).orElse(null))
                .recruitment(recruitmentRepository.findById(dto.recruitmentId()).orElse(null))
                .answers(dto.answers())
                .build();
    }

    public ApplicationResponseDTO toApplicationResponseDTO(Application application) {
        return new ApplicationResponseDTO(
                application.getOwner().getEmail(),
                application.getRecruitment().getTitle(),
                application.getAnswers()
        );
    }

    public List<ApplicationResponseDTO> toApplicationResponseDTOList(List<Application> applications) {
        return applications.stream()
                .map(this::toApplicationResponseDTO)
                .toList();
    }
}
