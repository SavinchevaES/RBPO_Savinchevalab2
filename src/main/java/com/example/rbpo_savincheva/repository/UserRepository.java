package com.example.rbpo_savincheva.repository;
import com.example.rbpo_savincheva.entity.User;
import org.springframework.stereotype.Repository;
@Repository public class UserRepository extends InMemoryRepository<User> { public UserRepository() { super(User::id); } }
