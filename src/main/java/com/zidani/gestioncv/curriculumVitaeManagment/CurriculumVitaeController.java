package com.zidani.gestioncv.curriculumVitaeManagment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Tag(name = "CurriculumVitae", description = "CurriculumVitae Management Api")
@RestController
@RequestMapping("/api/v1/cvs")
@RequiredArgsConstructor
public class CurriculumVitaeController {
    private final CurriculumVitaeService curriculumVitaeService;

    @PostMapping("/create")
    @Operation(summary = "Create a new Curriculum Vitae", description = "Endpoint to create a new Curriculum Vitae")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curriculum Vitae created successfully"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Long> createCV(
            @RequestParam String email
    ) {
        Long cvId = curriculumVitaeService.createCV(email);
        return ResponseEntity.ok(cvId);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing Curriculum Vitae", description = "Endpoint to delete an existing Curriculum Vitae")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Curriculum Vitae deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Curriculum Vitae not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteCV(
            @PathVariable Long id
    ) {
        curriculumVitaeService.deleteCv(id);
        return ResponseEntity.noContent().build();
    }
}
