package com.example.rbpo_savincheva.service;

import com.example.rbpo_savincheva.dto.Requests.TaskRequest;
import com.example.rbpo_savincheva.entity.VolunteerTask;
import com.example.rbpo_savincheva.repository.*;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class VolunteerTaskService {
  private final VolunteerTaskRepository repository;
  private final VolunteerCampaignRepository campaigns;
  private final VolunteerCleanupService cleanup;

  public VolunteerTaskService(
      VolunteerTaskRepository repository,
      VolunteerCampaignRepository campaigns,
      VolunteerCleanupService cleanup) {
    this.repository = repository;
    this.campaigns = campaigns;
    this.cleanup = cleanup;
  }

  public List<VolunteerTask> findAll() {
    return repository.findAll();
  }

  public VolunteerTask find(long id) {
    return repository.findById(id).orElseThrow(() -> new NotFoundException("Volunteer task", id));
  }

  public VolunteerTask create(TaskRequest r) {
    validate(r);
    Instant now = Instant.now();
    return repository.save(
        new VolunteerTask(
            repository.nextId(),
            r.campaignId(),
            r.requiredParticipants(),
            r.startAt(),
            r.durationHours(),
            now,
            now));
  }

  public VolunteerTask patch(long id, TaskRequest r) {
    VolunteerTask o = find(id);
    TaskRequest m =
        new TaskRequest(
            r.campaignId() == null ? o.campaignId() : r.campaignId(),
            r.requiredParticipants() == null ? o.requiredParticipants() : r.requiredParticipants(),
            r.startAt() == null ? o.startAt() : r.startAt(),
            r.durationHours() == null ? o.durationHours() : r.durationHours());
    validate(m);
    return repository.save(
        new VolunteerTask(
            id,
            m.campaignId(),
            m.requiredParticipants(),
            m.startAt(),
            m.durationHours(),
            o.createdAt(),
            Instant.now()));
  }

  public void delete(long id) {
    find(id);
    cleanup.deleteTask(id);
  }

  private void validate(TaskRequest r) {
    if (r.campaignId() == null
        || r.requiredParticipants() == null
        || r.requiredParticipants() < 1
        || r.startAt() == null
        || r.durationHours() == null
        || r.durationHours() < 1)
      throw new IllegalArgumentException(
          "campaignId, positive requiredParticipants, startAt and positive durationHours are"
              + " required");
    if (campaigns.findById(r.campaignId()).isEmpty())
      throw new NotFoundException("Volunteer campaign", r.campaignId());
  }
}
