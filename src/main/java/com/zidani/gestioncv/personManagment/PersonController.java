package com.zidani.gestioncv.personManagment;

import com.zidani.gestioncv.authenticationManagment.tokenManagement.AuthorizationService;
import com.zidani.gestioncv.config.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Person", description = "Person Management Api")
@RestController
@RequestMapping("/api/v1/management/person")
@RequiredArgsConstructor
@CrossOrigin
//@CrossOrigin(origins = "http://localhost:8080")
public class PersonController {
    private final PersonService personService;
    private final AuthorizationService authorizationService;
    private final JwtService jwtService;

    @Operation(
            summary = "Add a new person",
            description = "Endpoint to add a new person to the system."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Person successfully added"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add")
    public ResponseEntity<Void> addPerson(@RequestBody PersonRequest personRequest) {
        personService.addPerson(personRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Get all persons",
            description = "Endpoint to retrieve a list of all persons."
    )
    @ApiResponse(responseCode = "200", description = "List of persons retrieved successfully")
    @GetMapping("/all")
    public ResponseEntity<List<PersonResponse>> getAllPersons() {
        List<PersonResponse> persons = personService.getAllPersons();
        return ResponseEntity.ok(persons);
    }

    @Operation(
            summary = "Search persons by first name",
            description = "Endpoint to search persons by their first name."
    )
    @ApiResponse(responseCode = "200", description = "Persons retrieved successfully")
    @GetMapping("/search/firstName")
    public Page<PersonResponse>  searchPersonByFirstName(@RequestParam String firstName,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size
    ) {
        return personService.getPersonsByFirstName(page, size, firstName);
    }

    @Operation(
            summary = "Search persons by last name",
            description = "Endpoint to search persons by their last name."
    )
    @ApiResponse(responseCode = "200", description = "Persons retrieved successfully")
    @GetMapping("/search/lastName")
    public Page<PersonResponse> searchPersonByLastName(@RequestParam String lastName,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size
                                                                       ) {
        return personService.getPersonsByLastName(page, size, lastName);
    }

    @Operation(
            summary = "Search persons by experience",
            description = "Endpoint to search persons by their experience."
    )
    @ApiResponse(responseCode = "200", description = "Persons retrieved successfully")
    @GetMapping("/search/experience")
    public Page<PersonResponse> searchByExperience(@RequestParam String experience,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size
    ) {
        return personService.getPersonsByExperience(page, size, experience);
    }


    @Operation(
            summary = "Delete a person by email",
            description = "Endpoint to delete a person by their email."
    )
    @ApiResponse(responseCode = "200", description = "Person deleted successfully")
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<PersonResponse> deletePerson(@PathVariable String email) {
        PersonResponse deletedPerson = personService.deletePerson(email);
        return ResponseEntity.ok(deletedPerson);
    }

    @Operation(
            summary = "Update person details",
            description = "Endpoint to update details of an existing person."
    )
    @ApiResponse(responseCode = "200", description = "Person details updated successfully")
    @PutMapping("/update")
    public ResponseEntity<?> updatePersonDetails(HttpServletRequest httpServletRequest ,@RequestBody PersonRequest updatedPerson) {
        String username = jwtService.extractUsernameAndBearerToken(httpServletRequest);

        Long personId = personService.getPersonByUsername(username).get().getId();
        if(authorizationService.hasPersonRight(username,personId)) {
            PersonResponse updatedResponse = personService.updatePersonDetails(updatedPerson);
            return ResponseEntity.ok(updatedResponse);
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Vous n'êtes pas autorisé à modifier cette personne.");
        }
    }

    @Operation(
            summary = "get a persons cv",
            description = "Endpoint to retrieve persons cv ."
    )
    @ApiResponse(responseCode = "200", description = "cv retrieved successfully")
    @GetMapping("/cv")
    public ResponseEntity<String> retrieveCv(@RequestParam String email) {
        var cv = personService.getPersonCv(email);
        return ResponseEntity.ok(cv.toString());
    }

    @Operation(
            summary = "get a person details",
            description = "Endpoint to retrieve persons details ."
    )
    @ApiResponse(responseCode = "200", description = "person details retrieved successfully")
    @GetMapping("/details")
    public ResponseEntity<PersonResponse> retrievePersonDetails(@RequestParam String email) {
        var person = personService.getPersonDetails(email);
        return ResponseEntity.ok(person);
    }

    @Operation(
            summary = "get paginated persons list ",
            description = "Endpoint to retrieve paginated persons list ."
    )
    @ApiResponse(responseCode = "200", description = "persons page with correct params retrieved successfully")
    @GetMapping("/paginated-persons")
    public Page<PersonResponse> getAllPersons(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return personService.getPersonsPagination(page, size);
    }

}
