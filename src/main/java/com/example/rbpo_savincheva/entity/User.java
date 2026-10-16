package com.example.rbpo_savincheva.entity;

import java.time.Instant;

public record User(Long id, String name, String email, Instant createdAt) {}
