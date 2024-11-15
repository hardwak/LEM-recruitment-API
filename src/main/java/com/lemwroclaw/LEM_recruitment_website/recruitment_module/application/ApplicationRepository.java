package com.lemwroclaw.LEM_recruitment_website.recruitment_module.application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByOwnerEmail(String ownerEmail);
//    List<Application> findAllByOwnerId(Long ownerId);
//    List<Application> findAllByRecruitmentId(Long recruitmentId);

    @Query("SELECT a FROM Application a WHERE "
            + "(:userId IS NULL OR a.owner.id = :userId) AND "
            + "(:recruitmentId IS NULL OR a.recruitment.id = :recruitmentId)")
    List<Application> findApplicationsByUserIdAndRecruitmentId(
            @Param("userId") Long userId,
            @Param("recruitmentId") Long recruitmentId
    );

}
