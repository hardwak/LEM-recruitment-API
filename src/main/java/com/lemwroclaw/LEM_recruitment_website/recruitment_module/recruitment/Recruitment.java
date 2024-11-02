package com.lemwroclaw.LEM_recruitment_website.recruitment_module.recruitment;

import com.lemwroclaw.LEM_recruitment_website.recruitment_module.application.Application;
import com.lemwroclaw.LEM_recruitment_website.recruitment_module.task.Task;
import com.lemwroclaw.LEM_recruitment_website.recruitment_module.department.Department;
import com.lemwroclaw.LEM_recruitment_website.user.User;
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
    @JoinColumn(
            name = "department_id",
            nullable = false
    )
    private Department department;
    private Date startDate;
    private Date endDate;
    private Boolean hidden;
    @ManyToMany(mappedBy = "recruitments")
    private List<Task> tasks;
    @OneToMany(
            mappedBy = "recruitment",
            cascade = CascadeType.ALL
    )
    private List<Application> applications;

}
