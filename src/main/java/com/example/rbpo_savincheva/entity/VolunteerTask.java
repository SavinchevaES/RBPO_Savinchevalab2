package com.example.rbpo_savincheva.entity;

import java.time.Instant;
import java.time.LocalDateTime;

public record VolunteerTask(Long id, Long campaignId, int requiredParticipants, LocalDateTime startAt,
                            int durationHours, Instant createdAt, Instant updatedAt) {
}
