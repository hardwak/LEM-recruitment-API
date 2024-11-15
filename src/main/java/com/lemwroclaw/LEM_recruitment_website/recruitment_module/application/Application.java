package com.lemwroclaw.LEM_recruitment_website.recruitment_module.application;

import com.lemwroclaw.LEM_recruitment_website.recruitment_module.recruitment.Recruitment;
import com.lemwroclaw.LEM_recruitment_website.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Application {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User owner;
    @ManyToOne
    @JoinColumn(
            name = "recruitment_id",
            nullable = false
    )
    private Recruitment recruitment;
    private String answers;

}
