package com.example.rbpo_savincheva.service;

import com.example.rbpo_savincheva.dto.Requests.CampaignRequest;
import com.example.rbpo_savincheva.entity.VolunteerCampaign;
import com.example.rbpo_savincheva.repository.*;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

@Service public class VolunteerCampaignService {
    private final VolunteerCampaignRepository repository; private final UserRepository users; private final VolunteerCleanupService cleanup;
    public VolunteerCampaignService(VolunteerCampaignRepository repository, UserRepository users, VolunteerCleanupService cleanup) { this.repository = repository; this.users = users; this.cleanup = cleanup; }
    public List<VolunteerCampaign> findAll() { return repository.findAll(); }
    public VolunteerCampaign find(long id) { return repository.findById(id).orElseThrow(() -> new NotFoundException("Volunteer campaign", id)); }
    public VolunteerCampaign create(CampaignRequest r) { validate(r); Instant now = Instant.now(); return repository.save(new VolunteerCampaign(repository.nextId(), r.title(), r.startDate(), r.endDate(), r.coordinatorId(), r.status(), now, now)); }
    public VolunteerCampaign patch(long id, CampaignRequest r) { VolunteerCampaign o = find(id); CampaignRequest merged = new CampaignRequest(r.title() == null ? o.title() : r.title(), r.startDate() == null ? o.startDate() : r.startDate(), r.endDate() == null ? o.endDate() : r.endDate(), r.coordinatorId() == null ? o.coordinatorId() : r.coordinatorId(), r.status() == null ? o.status() : r.status()); validate(merged); return repository.save(new VolunteerCampaign(id, merged.title(), merged.startDate(), merged.endDate(), merged.coordinatorId(), merged.status(), o.createdAt(), Instant.now())); }
    public void delete(long id) { find(id); cleanup.deleteCampaign(id); }
    private void validate(CampaignRequest r) { if (r.title() == null || r.title().isBlank() || r.startDate() == null || r.endDate() == null || r.status() == null || r.status().isBlank() || r.coordinatorId() == null) throw new IllegalArgumentException("title, dates, coordinatorId and status are required"); if (r.endDate().isBefore(r.startDate())) throw new IllegalArgumentException("endDate must not be before startDate"); if (users.findById(r.coordinatorId()).isEmpty()) throw new NotFoundException("User", r.coordinatorId()); }
}
