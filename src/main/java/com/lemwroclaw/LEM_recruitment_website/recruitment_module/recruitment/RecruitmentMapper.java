package com.lemwroclaw.LEM_recruitment_website.recruitment_module.recruitment;

import com.lemwroclaw.LEM_recruitment_website.recruitment_module.department.DepartmentRepository;
import com.lemwroclaw.LEM_recruitment_website.recruitment_module.recruitment.dto.RecruitmentCreationDTO;
import com.lemwroclaw.LEM_recruitment_website.recruitment_module.recruitment.dto.RecruitmentResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class RecruitmentMapper {

    private final DepartmentRepository departmentRepository;

    public RecruitmentMapper(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Recruitment toRecruitment(RecruitmentCreationDTO dto) {

        return Recruitment.builder()
                .title(dto.title())
                .description(dto.description())
                .department(departmentRepository.findByName(dto.department()))
                .startDate(dto.startDate())
                .endDate(dto.endDate())
                .build();
    }

    public RecruitmentResponseDTO toRecruitmentResponseDTO(Recruitment recruitment) {
        return new RecruitmentResponseDTO(
                recruitment.getTitle(),
                recruitment.getDescription(),
                recruitment.getDepartment().getName(),
                recruitment.getStartDate(),
                recruitment.getEndDate()
                );
    }
}
