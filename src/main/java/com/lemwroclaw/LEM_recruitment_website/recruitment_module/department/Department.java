package com.lemwroclaw.LEM_recruitment_website.recruitment_module.department;

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
public class Department {
    @Id
    @GeneratedValue
    private Long id;
    @Column(
            unique = true,
            nullable = false
    )
    private String name;
    @OneToMany(
            mappedBy = "department",
            cascade = CascadeType.ALL
    )
    private List<Recruitment> recruitments;
}
