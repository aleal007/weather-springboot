package com.kapsch.weather.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kapsch.weather.backend.entity.Master;

public interface MasterRepository extends JpaRepository<Master, Long> {
}
