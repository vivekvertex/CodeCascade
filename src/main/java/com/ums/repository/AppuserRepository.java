package com.ums.repository;

import com.ums.entity.Appuser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppuserRepository extends JpaRepository<Appuser, Long> {


     Optional<Appuser> findByUsername(String username);
}