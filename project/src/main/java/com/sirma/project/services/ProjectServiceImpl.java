package com.sirma.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirma.project.exceptions.ProjectNotFoundException;
import com.sirma.project.models.Project;
import com.sirma.project.repositories.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {
@Autowired
	private ProjectRepository projectRepository;

@Override
public Project createProject(Project project) {
	
	return projectRepository.save(project);
}

@Override
public Project getProjectById(Long id) {
	
	return projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("Project not found with id: " + id));
}

@Override
public List<Project> getAllProjects() {
	return projectRepository.findAll();
}

@Override
public Project updateProject(Long id,Project project) {
	Project existingProject=projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("Project not found with id: " + id));
	existingProject.setName(project.getName());
	existingProject.setDescription(project.getDescription());
	existingProject.setStartDate(project.getStartDate());
	existingProject.setEndDate(project.getEndDate());
	return projectRepository.save(existingProject);
}

@Override
public void deleteProject(Long id) {
	
 projectRepository.deleteById(id);
}

}
