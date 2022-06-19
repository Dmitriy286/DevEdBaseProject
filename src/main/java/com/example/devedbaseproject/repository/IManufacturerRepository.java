package com.example.devedbaseproject.repository;

import com.example.devedbaseproject.models.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IManufacturerRepository extends JpaRepository <Manufacturer, Long> {
}
