package com.example.bank.repositories;

import com.example.bank.entities.Role;
import com.example.bank.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
