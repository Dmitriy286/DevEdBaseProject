package com.example.devedbaseproject.repository;

import com.example.devedbaseproject.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository  extends JpaRepository<Category, Long> {

}
