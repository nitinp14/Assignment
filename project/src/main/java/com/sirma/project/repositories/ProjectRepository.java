package com.sirma.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sirma.project.models.Project;
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
