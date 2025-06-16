package com.example.planify.controller;

import com.example.planify.exceptions.APIExceptionResponse;
import com.example.planify.model.Notebook;
import com.example.planify.model.User;
import com.example.planify.service.NotebookService;
import com.example.planify.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/notebooks")
public class NotebookController {

    private final NotebookService notebookService;
    private final UserService userService;

    public NotebookController(NotebookService notebookService, UserService userService) {
        this.notebookService = notebookService;
        this.userService = userService;
    }

    @Operation(
            summary = "Get all notebooks by user ID",
            description = "Returns all notebooks for a specific user based on userId."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched notebooks"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping()
    public ResponseEntity<?> getNotebooks(@RequestParam Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(notebookService.findNotebookByUserId(userId));
    }

    @Operation(
            summary = "Get notebook by name",
            description = "Returns a single notebook matching the given name."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched notebook"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/notebook/{name}")
    public ResponseEntity<?> getNotebookByName(@PathVariable String name) {
        Notebook notebook = notebookService.findNotebookByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(notebook);
    }

    @Operation(
            summary = "Add a new notebook",
            description = "Creates and saves a new notebook in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notebook created successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/add")
    public ResponseEntity<?> addNotebook(@RequestBody Notebook notebook) throws APIExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(notebookService.addNotebook(notebook));
    }

    @Operation(
            summary = "Delete a notebook",
            description = "Removes an existing notebook from the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notebook deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/delete")
    public ResponseEntity<?> deleteNotebook(@RequestBody Notebook notebook) {
        return ResponseEntity.status(HttpStatus.OK).body(notebookService.removeNotebook(notebook));
    }

    @Operation(
            summary = "Update a notebook",
            description = "Updates an existing notebook's information."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notebook updated successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/update")
    public ResponseEntity<?> updateNotebook(@RequestBody Notebook notebook) {
        return ResponseEntity.status(HttpStatus.OK).body(notebookService.updateNotebook(notebook));
    }
}
