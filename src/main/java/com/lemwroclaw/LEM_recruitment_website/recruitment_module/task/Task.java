package com.lemwroclaw.LEM_recruitment_website.recruitment_module.task;

import com.lemwroclaw.LEM_recruitment_website.recruitment_module.recruitment.Recruitment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @ManyToMany
    @JoinTable(
            name = "recruitment_tasks",
            joinColumns = {
                    @JoinColumn(name = "task_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "recruitment_id")
            }
    )
    private List<Recruitment> recruitments;
}
