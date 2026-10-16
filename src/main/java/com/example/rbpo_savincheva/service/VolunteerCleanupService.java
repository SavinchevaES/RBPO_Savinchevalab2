package com.example.rbpo_savincheva.service;

import com.example.rbpo_savincheva.repository.*;
import org.springframework.stereotype.Service;

@Service
public class VolunteerCleanupService {
  private final UserRepository users;
  private final VolunteerCampaignRepository campaigns;
  private final VolunteerTaskRepository tasks;
  private final VolunteerResponseRepository responses;
  private final ParticipationReportRepository reports;

  public VolunteerCleanupService(
      UserRepository users,
      VolunteerCampaignRepository campaigns,
      VolunteerTaskRepository tasks,
      VolunteerResponseRepository responses,
      ParticipationReportRepository reports) {
    this.users = users;
    this.campaigns = campaigns;
    this.tasks = tasks;
    this.responses = responses;
    this.reports = reports;
  }

  public void deleteUser(long id) {
    campaigns.findAll().stream()
        .filter(c -> c.coordinatorId().equals(id))
        .map(c -> c.id())
        .toList()
        .forEach(this::deleteCampaign);
    responses.findAll().stream()
        .filter(r -> r.volunteerId().equals(id))
        .map(r -> r.id())
        .toList()
        .forEach(this::deleteResponse);
    users.deleteById(id);
  }

  public void deleteCampaign(long id) {
    tasks.findAll().stream()
        .filter(t -> t.campaignId().equals(id))
        .map(t -> t.id())
        .toList()
        .forEach(this::deleteTask);
    campaigns.deleteById(id);
  }

  public void deleteTask(long id) {
    responses.findAll().stream()
        .filter(r -> r.taskId().equals(id))
        .map(r -> r.id())
        .toList()
        .forEach(this::deleteResponse);
    tasks.deleteById(id);
  }

  public void deleteResponse(long id) {
    reports.findAll().stream()
        .filter(r -> r.responseId().equals(id))
        .map(r -> r.id())
        .toList()
        .forEach(reports::deleteById);
    responses.deleteById(id);
  }

  public void deleteReport(long id) {
    reports.deleteById(id);
  }
}
