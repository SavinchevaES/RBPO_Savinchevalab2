package com.example.rbpo_savincheva.controller;
import com.example.rbpo_savincheva.dto.Requests.ReportRequest;
import com.example.rbpo_savincheva.entity.ParticipationReport;
import com.example.rbpo_savincheva.service.ParticipationReportService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/participation-reports") public class ParticipationReportController {
    private final ParticipationReportService service; public ParticipationReportController(ParticipationReportService service) { this.service = service; }
    @GetMapping public List<ParticipationReport> all() { return service.findAll(); }
    @GetMapping("/{id}") public ParticipationReport one(@PathVariable long id) { return service.find(id); }
    @PostMapping public ResponseEntity<ParticipationReport> create(@RequestBody ReportRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request)); }
    @PatchMapping("/{id}") public ParticipationReport patch(@PathVariable long id, @RequestBody ReportRequest request) { return service.patch(id, request); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable long id) { service.delete(id); }
}
