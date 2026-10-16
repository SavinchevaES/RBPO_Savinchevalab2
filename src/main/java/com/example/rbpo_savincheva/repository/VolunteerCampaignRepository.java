package com.example.rbpo_savincheva.repository;
import com.example.rbpo_savincheva.entity.VolunteerCampaign;
import org.springframework.stereotype.Repository;
@Repository public class VolunteerCampaignRepository extends InMemoryRepository<VolunteerCampaign> { public VolunteerCampaignRepository() { super(VolunteerCampaign::id); } }
