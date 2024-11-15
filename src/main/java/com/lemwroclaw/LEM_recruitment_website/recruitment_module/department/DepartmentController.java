package com.lemwroclaw.LEM_recruitment_website.recruitment_module.department;

import com.lemwroclaw.LEM_recruitment_website.recruitment_module.department.dto.DepartmentDTO;
import com.lemwroclaw.LEM_recruitment_website.recruitment_module.department.dto.DepartmentDTOId;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public DepartmentDTO addDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        return departmentService.addDepartment(departmentDTO);
    }

    @GetMapping
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public DepartmentDTOId getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public void deleteDepartmentById(@PathVariable Long id) {
        departmentService.deleteDepartmentById(id);
    }
}
