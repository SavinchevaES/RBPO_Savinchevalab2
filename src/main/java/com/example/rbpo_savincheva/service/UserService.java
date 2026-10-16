package com.example.rbpo_savincheva.service;

import com.example.rbpo_savincheva.dto.Requests.UserRequest;
import com.example.rbpo_savincheva.entity.User;
import com.example.rbpo_savincheva.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

@Service public class UserService {
    private final UserRepository repository; private final VolunteerCleanupService cleanup;
    public UserService(UserRepository repository, VolunteerCleanupService cleanup) { this.repository = repository; this.cleanup = cleanup; }
    public List<User> findAll() { return repository.findAll(); }
    public User find(long id) { return repository.findById(id).orElseThrow(() -> new NotFoundException("User", id)); }
    public User create(UserRequest r) { validate(r.name(), r.email()); return repository.save(new User(repository.nextId(), r.name(), r.email(), Instant.now())); }
    public User patch(long id, UserRequest r) { User old = find(id); String name = r.name() == null ? old.name() : r.name(); String email = r.email() == null ? old.email() : r.email(); validate(name, email); return repository.save(new User(id, name, email, old.createdAt())); }
    public void delete(long id) { find(id); cleanup.deleteUser(id); }
    private void validate(String name, String email) { if (name == null || name.isBlank() || email == null || email.isBlank()) throw new IllegalArgumentException("name and email are required"); }
}
