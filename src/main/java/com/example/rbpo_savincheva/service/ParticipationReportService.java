package com.example.rbpo_savincheva.service;

import com.example.rbpo_savincheva.dto.Requests.ReportRequest;
import com.example.rbpo_savincheva.entity.ParticipationReport;
import com.example.rbpo_savincheva.repository.*;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ParticipationReportService {
  private final ParticipationReportRepository repository;
  private final VolunteerResponseRepository responses;
  private final VolunteerCleanupService cleanup;

  public ParticipationReportService(
      ParticipationReportRepository repository,
      VolunteerResponseRepository responses,
      VolunteerCleanupService cleanup) {
    this.repository = repository;
    this.responses = responses;
    this.cleanup = cleanup;
  }

  public List<ParticipationReport> findAll() {
    return repository.findAll();
  }

  public ParticipationReport find(long id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Participation report", id));
  }

  public ParticipationReport create(ReportRequest r) {
    validate(r);
    Instant now = Instant.now();
    return repository.save(
        new ParticipationReport(
            repository.nextId(),
            r.responseId(),
            r.actualHours(),
            r.confirmationStatus(),
            now,
            now));
  }

  public ParticipationReport patch(long id, ReportRequest r) {
    ParticipationReport o = find(id);
    ReportRequest m =
        new ReportRequest(
            r.responseId() == null ? o.responseId() : r.responseId(),
            r.actualHours() == null ? o.actualHours() : r.actualHours(),
            r.confirmationStatus() == null ? o.confirmationStatus() : r.confirmationStatus());
    validate(m);
    return repository.save(
        new ParticipationReport(
            id,
            m.responseId(),
            m.actualHours(),
            m.confirmationStatus(),
            o.createdAt(),
            Instant.now()));
  }

  public void delete(long id) {
    find(id);
    cleanup.deleteReport(id);
  }

  private void validate(ReportRequest r) {
    if (r.responseId() == null
        || r.actualHours() == null
        || r.actualHours() < 0
        || r.confirmationStatus() == null
        || r.confirmationStatus().isBlank())
      throw new IllegalArgumentException(
          "responseId, non-negative actualHours and confirmationStatus are required");
    if (responses.findById(r.responseId()).isEmpty())
      throw new NotFoundException("Volunteer response", r.responseId());
  }
}
