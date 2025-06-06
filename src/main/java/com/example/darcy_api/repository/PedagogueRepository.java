package com.example.darcy_api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.darcy_api.model.Pedagogue;

public interface PedagogueRepository extends JpaRepository<Pedagogue, UUID>{
}
