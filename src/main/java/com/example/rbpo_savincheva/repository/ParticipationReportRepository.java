package com.example.rbpo_savincheva.repository;
import com.example.rbpo_savincheva.entity.ParticipationReport;
import org.springframework.stereotype.Repository;
@Repository public class ParticipationReportRepository extends InMemoryRepository<ParticipationReport> { public ParticipationReportRepository() { super(ParticipationReport::id); } }
