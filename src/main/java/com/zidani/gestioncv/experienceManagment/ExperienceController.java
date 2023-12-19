package com.zidani.gestioncv.experienceManagment;

import com.zidani.gestioncv.authenticationManagment.tokenManagement.AuthorizationService;
import com.zidani.gestioncv.config.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Tag(name = "Experience", description = "Experience Management Api")
@RestController
@RequestMapping("/api/v1/experiences")
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceService experienceService;
    private final JwtService jwtService;
    private final AuthorizationService authorizationService;

    @PostMapping("/{cvId}")
    @Operation(summary = "Add a new experience to the CV", description = "Endpoint to add a new experience to the CV")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Experience added successfully"),
            @ApiResponse(responseCode = "404", description = "CV not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> addExperience(
            @PathVariable Long cvId,
            @RequestBody ExperienceRequest experienceRequest,
            HttpServletRequest servletRequest
    ) {
        String username = jwtService.extractUsernameAndBearerToken(servletRequest);

        if (authorizationService.hasExperienceRight(username, cvId)) {
            Long experienceId = experienceService.addExperience(cvId, experienceRequest);
            return ResponseEntity.ok(experienceId);
        }
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Forbidden ");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing experience", description = "Endpoint to update an existing experience")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Experience updated successfully"),
            @ApiResponse(responseCode = "404", description = "Experience not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> updateExperience(
            @PathVariable Long id,
            @RequestBody ExperienceRequest experienceRequest,
            HttpServletRequest servletRequest
    ) {
        String username = jwtService.extractUsernameAndBearerToken(servletRequest);

        if (authorizationService.hasExperienceRight(username, id)) {
            Experience updatedExperience = experienceService.updateExperience(id, experienceRequest);
            return ResponseEntity.ok(updatedExperience);
        }
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Forbidden ");    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing experience", description = "Endpoint to delete an existing experience")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Experience deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Experience not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> deleteExperience(
            @PathVariable Long id,
            HttpServletRequest servletRequest
    ) {
        String username = jwtService.extractUsernameAndBearerToken(servletRequest);

        if (authorizationService.hasExperienceRight(username, id)) {
           experienceService.deleteExperience(id);
            return ResponseEntity.ok("Experience supprimé");
        }
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Forbidden ");    }

    @GetMapping("/searchByExperienceTitle")
    public Page<?> searchByExperienceTitle(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        return experienceService.searchByExperienceTitle(title, PageRequest.of(page, size));
    }
}