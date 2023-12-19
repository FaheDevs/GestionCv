package com.zidani.gestioncv.curriculumVitaeManagment;

import com.zidani.gestioncv.authenticationManagment.tokenManagement.AuthorizationService;
import com.zidani.gestioncv.config.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CurriculumVitae", description = "CurriculumVitae Management Api")
@RestController
@RequestMapping("/api/v1/management/cvs")
@RequiredArgsConstructor
public class CurriculumVitaeController {
    private final CurriculumVitaeService curriculumVitaeService;
    private final JwtService jwtService;
    private final AuthorizationService authorizationService;



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
    public ResponseEntity<?> deleteCV(HttpServletRequest httpServletRequest, @PathVariable Long id) {
        String username = jwtService.extractUsernameAndBearerToken(httpServletRequest);

        if (authorizationService.hasCvRight(username,id)) {
            curriculumVitaeService.deleteCv(id);
            return ResponseEntity.ok().body("CV supprimé");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Vous n'êtes pas autorisé à supprimer ce CV.");
        }

    }


}
