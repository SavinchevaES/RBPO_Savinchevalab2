package com.example.rbpo_savincheva.repository;
import com.example.rbpo_savincheva.entity.VolunteerTask;
import org.springframework.stereotype.Repository;
@Repository public class VolunteerTaskRepository extends InMemoryRepository<VolunteerTask> { public VolunteerTaskRepository() { super(VolunteerTask::id); } }
