package com.example.rbpo_savincheva.entity;

import java.time.Instant;

public record ParticipationReport(
    Long id,
    Long responseId,
    int actualHours,
    String confirmationStatus,
    Instant createdAt,
    Instant updatedAt) {}
