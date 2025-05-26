package com.example.MTS1.api;

import com.example.MTS1.model.University;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "University Controller", description = "Operations related to Universities")
public interface UniversityControllerDocs {

    @Operation(summary = "Get all universities")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of universities retrieved successfully")
    })
    List<University> getAllUniversities();

    @Operation(summary = "Get university by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "University found"),
            @ApiResponse(responseCode = "404", description = "University not found")
    })
    ResponseEntity<University> getUniversityById(
            @Parameter(description = "ID of the university to retrieve", required = true)
            @PathVariable Long id);

    @Operation(summary = "Create a new university")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "University created successfully")
    })
    University createUniversity(
            @Parameter(description = "University to create", required = true)
            @RequestBody University university);

    @Operation(summary = "Create multiple universities")
    List<University> createUniversitiesBatch(
            @Parameter(description = "List of universities to create", required = true)
            @RequestBody List<University> universities);

    @Operation(summary = "Update an existing university")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "University updated successfully"),
            @ApiResponse(responseCode = "404", description = "University not found")
    })
    ResponseEntity<University> updateUniversity(
            @Parameter(description = "ID of the university to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated university data", required = true)
            @RequestBody University university);

    @Operation(summary = "Update university location")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "University location updated successfully"),
            @ApiResponse(responseCode = "404", description = "University not found")
    })
    ResponseEntity<University> updateUniversityLocation(
            @Parameter(description = "ID of the university to update location", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated location", required = true)
            @RequestBody University university);

    @Operation(summary = "Patch university")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "University patched successfully"),
            @ApiResponse(responseCode = "404", description = "University not found")
    })
    ResponseEntity<University> patchUniversity(
            @Parameter(description = "ID of the university to patch", required = true)
            @PathVariable Long id,
            @Parameter(description = "Partial university data", required = true)
            @RequestBody University university);

    @Operation(summary = "Patch university name")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "University name patched successfully"),
            @ApiResponse(responseCode = "404", description = "University not found")
    })
    ResponseEntity<University> patchUniversityName(
            @Parameter(description = "ID of the university to patch name", required = true)
            @PathVariable Long id,
            @Parameter(description = "New university name", required = true)
            @RequestBody University university);

    @Operation(summary = "Delete university by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "University deleted successfully"),
            @ApiResponse(responseCode = "404", description = "University not found")
    })
    ResponseEntity<Void> deleteUniversity(
            @Parameter(description = "ID of the university to delete", required = true)
            @PathVariable Long id);

    @Operation(summary = "Delete multiple universities")
    ResponseEntity<Void> deleteUniversitiesBatch(
            @Parameter(description = "List of university IDs to delete", required = true)
            @RequestBody List<Long> ids);
}
