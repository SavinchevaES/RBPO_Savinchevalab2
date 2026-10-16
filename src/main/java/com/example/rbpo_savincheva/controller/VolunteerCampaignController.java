package com.example.rbpo_savincheva.controller;
import com.example.rbpo_savincheva.dto.Requests.CampaignRequest;
import com.example.rbpo_savincheva.entity.VolunteerCampaign;
import com.example.rbpo_savincheva.service.VolunteerCampaignService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/volunteer-campaigns") public class VolunteerCampaignController {
    private final VolunteerCampaignService service; public VolunteerCampaignController(VolunteerCampaignService service) { this.service = service; }
    @GetMapping public List<VolunteerCampaign> all() { return service.findAll(); }
    @GetMapping("/{id}") public VolunteerCampaign one(@PathVariable long id) { return service.find(id); }
    @PostMapping public ResponseEntity<VolunteerCampaign> create(@RequestBody CampaignRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request)); }
    @PatchMapping("/{id}") public VolunteerCampaign patch(@PathVariable long id, @RequestBody CampaignRequest request) { return service.patch(id, request); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable long id) { service.delete(id); }
}
