package com.lemwroclaw.LEM_recruitment_website.recruitment_module.recruitment;

import com.lemwroclaw.LEM_recruitment_website.recruitment_module.task.Task;
import com.lemwroclaw.LEM_recruitment_website.recruitment_module.department.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recruitment {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    private Date startDate;
    private Date endDate;
    private Boolean hidden;
    @ManyToMany(mappedBy = "recruitments")
    private List<Task> tasks;

}
