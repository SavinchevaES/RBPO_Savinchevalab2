package com.example.rbpo_savincheva.controller;

import com.example.rbpo_savincheva.dto.Requests.ResponseRequest;
import com.example.rbpo_savincheva.entity.VolunteerResponse;
import com.example.rbpo_savincheva.service.VolunteerResponseService;
import java.util.List;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/volunteer-responses")
public class VolunteerResponseController {
  private final VolunteerResponseService service;

  public VolunteerResponseController(VolunteerResponseService service) {
    this.service = service;
  }

  @GetMapping
  public List<VolunteerResponse> all() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public VolunteerResponse one(@PathVariable long id) {
    return service.find(id);
  }

  @PostMapping
  public ResponseEntity<VolunteerResponse> create(@RequestBody ResponseRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
  }

  @PatchMapping("/{id}")
  public VolunteerResponse patch(@PathVariable long id, @RequestBody ResponseRequest request) {
    return service.patch(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable long id) {
    service.delete(id);
  }
}
