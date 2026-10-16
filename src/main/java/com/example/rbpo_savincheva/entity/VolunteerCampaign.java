package com.example.rbpo_savincheva.entity;

import java.time.Instant;
import java.time.LocalDate;

public record VolunteerCampaign(
    Long id,
    String title,
    LocalDate startDate,
    LocalDate endDate,
    Long coordinatorId,
    String status,
    Instant createdAt,
    Instant updatedAt) {}
