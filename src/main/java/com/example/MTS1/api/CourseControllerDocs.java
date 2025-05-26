package com.example.MTS1.api;

import com.example.MTS1.model.Course;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Course Controller", description = "Operations related to Courses")
public interface CourseControllerDocs {

    @Operation(summary = "Get all courses")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of courses retrieved successfully")
    })
    List<Course> getAllCourses();

    @Operation(summary = "Get course by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Course found"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    ResponseEntity<Course> getCourseById(
            @Parameter(description = "ID of the course to retrieve", required = true)
            @PathVariable Long id);

    @Operation(summary = "Create a new course")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Course created successfully")
    })
    Course createCourse(
            @Parameter(description = "Course to create", required = true)
            @RequestBody Course course);

    @Operation(summary = "Create multiple courses")
    List<Course> createCoursesBatch(
            @Parameter(description = "List of courses to create", required = true)
            @RequestBody List<Course> courses);

    @Operation(summary = "Update an existing course")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Course updated successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    ResponseEntity<Course> updateCourse(
            @Parameter(description = "ID of the course to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated course data", required = true)
            @RequestBody Course course);

    @Operation(summary = "Update course description")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Course description updated successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    ResponseEntity<Course> updateCourseDescription(
            @Parameter(description = "ID of the course to update description", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated description", required = true)
            @RequestBody Course course);

    @Operation(summary = "Patch course")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Course patched successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    ResponseEntity<Course> patchCourse(
            @Parameter(description = "ID of the course to patch", required = true)
            @PathVariable Long id,
            @Parameter(description = "Partial course data", required = true)
            @RequestBody Course course);

    @Operation(summary = "Patch course title")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Course title patched successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    ResponseEntity<Course> patchCourseTitle(
            @Parameter(description = "ID of the course to patch title", required = true)
            @PathVariable Long id,
            @Parameter(description = "New course title", required = true)
            @RequestBody Course course);

    @Operation(summary = "Delete course by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Course deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    ResponseEntity<Void> deleteCourse(
            @Parameter(description = "ID of the course to delete", required = true)
            @PathVariable Long id);

    @Operation(summary = "Delete multiple courses")
    ResponseEntity<Void> deleteCoursesBatch(
            @Parameter(description = "List of course IDs to delete", required = true)
            @RequestBody List<Long> ids);
}
