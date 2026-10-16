package com.example.rbpo_savincheva.repository;

import com.example.rbpo_savincheva.entity.VolunteerResponse;
import org.springframework.stereotype.Repository;

@Repository
public class VolunteerResponseRepository extends InMemoryRepository<VolunteerResponse> {
  public VolunteerResponseRepository() {
    super(VolunteerResponse::id);
  }
}
