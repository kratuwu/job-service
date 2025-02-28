package com.ata.job_service.repository;

import com.ata.job_service.entity.SalarySurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SalarySurveyRepository extends JpaRepository<SalarySurvey, Long>, JpaSpecificationExecutor<SalarySurvey> {

}