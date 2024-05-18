package com.sirma.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sirma.project.models.Project;
import com.sirma.project.services.ProjectServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;



@RestController
@RequestMapping("/api/projects")

public class ProjectController {
@Autowired
	private ProjectServiceImpl serviceImpl;

@GetMapping
@Operation(summary = "Get all projects")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the projects",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Project.class)) }),
        @ApiResponse(responseCode = "404", description = "Projects not found",
                content = @Content) })
public ResponseEntity<List<Project>> getAllProjects() {
    return ResponseEntity.ok(serviceImpl.getAllProjects());
}

@GetMapping("/{id}")
@Operation(summary = "Get a project by ID")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the project",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Project.class)) }),
        @ApiResponse(responseCode = "404", description = "Project not found",
                content = @Content) })
public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
    Project project = serviceImpl.getProjectById(id);
    if (project != null) {
        return ResponseEntity.ok(project);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}

@PostMapping
@Operation(summary = "Create a new project")
@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Project created",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Project.class)) }) })
public ResponseEntity<Project> createProject(@RequestBody Project project) {
    return ResponseEntity.status(HttpStatus.CREATED).body(serviceImpl.createProject(project));
}

@PutMapping("/{id}")
@Operation(summary = "Update a project by ID")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Project updated",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Project.class)) }),
        @ApiResponse(responseCode = "404", description = "Project not found",
                content = @Content) })
public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project) {
    Project updatedProject = serviceImpl.updateProject(id, project);
    if (updatedProject != null) {
        return ResponseEntity.ok(updatedProject);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}

@DeleteMapping("/{id}")
@Operation(summary = "Delete a project by ID")
@ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Project deleted",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Project not found",
                content = @Content) })
public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
	serviceImpl.deleteProject(id);
    return ResponseEntity.noContent().build();
}
}
