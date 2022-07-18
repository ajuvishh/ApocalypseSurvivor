package com.pwc.survivorcamp.dao;

import com.pwc.survivorcamp.model.ReportInfected;
import com.pwc.survivorcamp.model.Survivors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfectedRepo extends JpaRepository<ReportInfected, Integer> {
    int countByReportInfectedKey_infectedId(Survivors infectedId);
}
