package com.lemwroclaw.LEM_recruitment_website.recruitment_module.task;

import com.lemwroclaw.LEM_recruitment_website.recruitment_module.recruitment.Recruitment;
import com.lemwroclaw.LEM_recruitment_website.recruitment_module.recruitment.RecruitmentRepository;
import com.lemwroclaw.LEM_recruitment_website.recruitment_module.task.dto.TaskCreationDTO;
import com.lemwroclaw.LEM_recruitment_website.recruitment_module.task.dto.TaskResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskMapper {

    private final RecruitmentRepository recruitmentRepository;

    public TaskMapper(RecruitmentRepository recruitmentRepository) {
        this.recruitmentRepository = recruitmentRepository;
    }

    public Task toTask(TaskCreationDTO dto) {

        return Task
                .builder()
                .title(dto.title())
                .description(dto.description())
                .recruitments(recruitmentRepository.findAllById(dto.recruitments()))
                .build();
    }

    public TaskResponseDTO toTaskResponseDTO(Task task) {
        List<String> recruitments = null;

        if (task.getRecruitments() != null) {
            recruitments = task.getRecruitments()
                    .stream()
                    .map(Recruitment::getTitle)
                    .toList();
        }

        return new TaskResponseDTO(
                task.getTitle(),
                task.getDescription(),
                recruitments
        );
    }
}
