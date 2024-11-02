package com.lemwroclaw.LEM_recruitment_website.recruitment_module.department;

import com.lemwroclaw.LEM_recruitment_website.recruitment_module.department.dto.DepartmentDTO;
import com.lemwroclaw.LEM_recruitment_website.recruitment_module.department.dto.DepartmentDTOId;
import org.springframework.stereotype.Service;

@Service
public class DepartmentMapper {
    public DepartmentDTO toDepartmentDTO(Department department) {
        return new DepartmentDTO(department.getName());
    }

    public DepartmentDTOId toDepartmentDTOId(Department department) {
        return new DepartmentDTOId(department.getId(), department.getName());
    }
}
