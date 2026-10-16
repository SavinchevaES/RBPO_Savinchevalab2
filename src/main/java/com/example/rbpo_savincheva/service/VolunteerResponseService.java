package com.example.rbpo_savincheva.service;

import com.example.rbpo_savincheva.dto.Requests.ResponseRequest;
import com.example.rbpo_savincheva.entity.VolunteerResponse;
import com.example.rbpo_savincheva.repository.*;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

@Service public class VolunteerResponseService {
    private final VolunteerResponseRepository repository; private final VolunteerTaskRepository tasks; private final UserRepository users; private final VolunteerCleanupService cleanup;
    public VolunteerResponseService(VolunteerResponseRepository repository, VolunteerTaskRepository tasks, UserRepository users, VolunteerCleanupService cleanup) { this.repository = repository; this.tasks = tasks; this.users = users; this.cleanup = cleanup; }
    public List<VolunteerResponse> findAll() { return repository.findAll(); }
    public VolunteerResponse find(long id) { return repository.findById(id).orElseThrow(() -> new NotFoundException("Volunteer response", id)); }
    public VolunteerResponse create(ResponseRequest r) { validate(r); Instant now = Instant.now(); return repository.save(new VolunteerResponse(repository.nextId(), r.taskId(), r.volunteerId(), r.admissionStatus(), now, now)); }
    public VolunteerResponse patch(long id, ResponseRequest r) { VolunteerResponse o = find(id); ResponseRequest m = new ResponseRequest(r.taskId() == null ? o.taskId() : r.taskId(), r.volunteerId() == null ? o.volunteerId() : r.volunteerId(), r.admissionStatus() == null ? o.admissionStatus() : r.admissionStatus()); validate(m); return repository.save(new VolunteerResponse(id, m.taskId(), m.volunteerId(), m.admissionStatus(), o.createdAt(), Instant.now())); }
    public void delete(long id) { find(id); cleanup.deleteResponse(id); }
    private void validate(ResponseRequest r) { if (r.taskId() == null || r.volunteerId() == null || r.admissionStatus() == null || r.admissionStatus().isBlank()) throw new IllegalArgumentException("taskId, volunteerId and admissionStatus are required"); if (tasks.findById(r.taskId()).isEmpty()) throw new NotFoundException("Volunteer task", r.taskId()); if (users.findById(r.volunteerId()).isEmpty()) throw new NotFoundException("User", r.volunteerId()); }
}
