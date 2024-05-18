package com.sirma.project.services;

import java.util.List;

import com.sirma.project.models.Project;

public interface ProjectService {

	public Project createProject(Project project);
	
	public Project getProjectById(Long id);
	
	public List<Project> getAllProjects();
	
	public Project updateProject(Long id,Project project);
	
	public void deleteProject(Long id);
}
