package com.example.rbpo_savincheva.controller;
import com.example.rbpo_savincheva.dto.Requests.UserRequest;
import com.example.rbpo_savincheva.entity.User;
import com.example.rbpo_savincheva.service.UserService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/users") public class UserController {
    private final UserService service; public UserController(UserService service) { this.service = service; }
    @GetMapping public List<User> all() { return service.findAll(); }
    @GetMapping("/{id}") public User one(@PathVariable long id) { return service.find(id); }
    @PostMapping public ResponseEntity<User> create(@RequestBody UserRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request)); }
    @PatchMapping("/{id}") public User patch(@PathVariable long id, @RequestBody UserRequest request) { return service.patch(id, request); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable long id) { service.delete(id); }
}
