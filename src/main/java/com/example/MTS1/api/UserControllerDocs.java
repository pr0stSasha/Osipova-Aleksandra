package com.example.MTS1.api;

import com.example.MTS1.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "User Controller", description = "Operations related to Users")
public interface UserControllerDocs {

    @Operation(summary = "Get all users")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully")
    })
    List<User> getAllUsers();

    @Operation(summary = "Get user by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    ResponseEntity<User> getUserById(
            @Parameter(description = "ID of the user", required = true)
            @PathVariable Long id);

    @Operation(summary = "Create a new user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created successfully")
    })
    User createUser(
            @Parameter(description = "User to create", required = true)
            @RequestBody User user);

    @Operation(summary = "Create multiple users")
    List<User> createUsersBatch(
            @Parameter(description = "Users to create", required = true)
            @RequestBody List<User> users);

    @Operation(summary = "Update an existing user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    ResponseEntity<User> updateUser(
            @Parameter(description = "ID of the user", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated user data", required = true)
            @RequestBody User user);

    @Operation(summary = "Update username")
    ResponseEntity<User> patchUserName(
            @Parameter(description = "ID of the user", required = true)
            @PathVariable Long id,
            @Parameter(description = "New username", required = true)
            @RequestBody User user);

    @Operation(summary = "Patch user")
    ResponseEntity<User> patchUser(
            @Parameter(description = "ID of the user", required = true)
            @PathVariable Long id,
            @Parameter(description = "Partial user data", required = true)
            @RequestBody User user);

    @Operation(summary = "Patch user email")
    ResponseEntity<User> updateUserEmail(
            @Parameter(description = "ID of the user", required = true)
            @PathVariable Long id,
            @Parameter(description = "New email", required = true)
            @RequestBody User user);

    @Operation(summary = "Delete user by ID")
    ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID of the user", required = true)
            @PathVariable Long id);

    @Operation(summary = "Delete multiple users")
    ResponseEntity<Void> deleteUsersBatch(
            @Parameter(description = "List of user IDs", required = true)
            @RequestBody List<Long> ids);
}
