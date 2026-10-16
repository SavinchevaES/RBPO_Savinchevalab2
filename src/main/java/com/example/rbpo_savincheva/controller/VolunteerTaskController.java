package com.example.rbpo_savincheva.controller;
import com.example.rbpo_savincheva.dto.Requests.TaskRequest;
import com.example.rbpo_savincheva.entity.VolunteerTask;
import com.example.rbpo_savincheva.service.VolunteerTaskService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/volunteer-tasks") public class VolunteerTaskController {
    private final VolunteerTaskService service; public VolunteerTaskController(VolunteerTaskService service) { this.service = service; }
    @GetMapping public List<VolunteerTask> all() { return service.findAll(); }
    @GetMapping("/{id}") public VolunteerTask one(@PathVariable long id) { return service.find(id); }
    @PostMapping public ResponseEntity<VolunteerTask> create(@RequestBody TaskRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request)); }
    @PatchMapping("/{id}") public VolunteerTask patch(@PathVariable long id, @RequestBody TaskRequest request) { return service.patch(id, request); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable long id) { service.delete(id); }
}
