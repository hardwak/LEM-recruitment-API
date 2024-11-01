package com.lemwroclaw.LEM_recruitment_website.recruitment_module.department;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    public Department findByName(String name);
}
