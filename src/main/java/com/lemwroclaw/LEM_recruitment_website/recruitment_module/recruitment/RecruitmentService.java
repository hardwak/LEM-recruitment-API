package com.lemwroclaw.LEM_recruitment_website.recruitment_module.recruitment;

import com.lemwroclaw.LEM_recruitment_website.recruitment_module.recruitment.dto.RecruitmentCreationDTO;
import com.lemwroclaw.LEM_recruitment_website.recruitment_module.recruitment.dto.RecruitmentResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final RecruitmentMapper recruitmentMapper;


    public RecruitmentService(RecruitmentRepository recruitmentRepository, RecruitmentMapper recruitmentMapper) {
        this.recruitmentRepository = recruitmentRepository;
        this.recruitmentMapper = recruitmentMapper;
    }

    //TODO returns name of department if it does not exists
    public ResponseEntity<RecruitmentCreationDTO> createRecruitment(RecruitmentCreationDTO recruitmentCreationDTO) {
        Recruitment recruitment = recruitmentMapper.toRecruitment(recruitmentCreationDTO);

        if (recruitment.getDepartment() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        recruitmentRepository.save(recruitment);
        return new ResponseEntity<>(recruitmentCreationDTO, HttpStatus.CREATED);
    }

    public ResponseEntity<List<RecruitmentResponseDTO>> getAllRecruitments() {
        List<RecruitmentResponseDTO> recruitments = recruitmentRepository.findAll()
                .stream()
                .map(recruitmentMapper::toRecruitmentResponseDTO)
                .toList();

        return new ResponseEntity<>(recruitments, HttpStatus.OK);
    }

    public ResponseEntity<RecruitmentResponseDTO> getRecruitmentById(Long id) {
        Recruitment recruitment = recruitmentRepository.findById(id).orElse(null);
        if (recruitment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(recruitmentMapper.toRecruitmentResponseDTO(recruitment), HttpStatus.OK);
    }

    public void deleteRecruitmentById(Long id) {
        recruitmentRepository.deleteById(id);
    }
}
