package com.lemwroclaw.LEM_recruitment_website.recruitment_module.department;

import com.lemwroclaw.LEM_recruitment_website.recruitment_module.department.dto.DepartmentDTO;
import com.lemwroclaw.LEM_recruitment_website.recruitment_module.department.dto.DepartmentDTOId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    public DepartmentDTO addDepartment(DepartmentDTO departmentDTO) {
        var department = new Department();
        department.setName(departmentDTO.name());
        departmentRepository.save(department);
        return departmentDTO;
    }

    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toDepartmentDTO)
                .toList();
    }

    public DepartmentDTOId getDepartmentById(Long id) {
        return departmentMapper.toDepartmentDTOId(departmentRepository.findById(id).orElse(null));
    }

    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }
}
