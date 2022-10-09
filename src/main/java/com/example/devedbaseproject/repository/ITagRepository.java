package com.example.devedbaseproject.repository;

import com.example.devedbaseproject.models.Employee;
import com.example.devedbaseproject.models.Role;
import com.example.devedbaseproject.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByName(String name);

}
