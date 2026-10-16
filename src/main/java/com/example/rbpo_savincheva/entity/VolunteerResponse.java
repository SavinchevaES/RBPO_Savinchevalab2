package com.example.rbpo_savincheva.entity;

import java.time.Instant;

public record VolunteerResponse(
    Long id,
    Long taskId,
    Long volunteerId,
    String admissionStatus,
    Instant createdAt,
    Instant updatedAt) {}
