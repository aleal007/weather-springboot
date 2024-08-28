package com.kapsch.weather.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kapsch.weather.backend.entity.Detail;

public interface DetailRepository extends JpaRepository<Detail, Long> {
}
