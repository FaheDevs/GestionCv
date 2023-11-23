package com.zidani.gestioncv.personManagment;

import com.zidani.gestioncv.curriculumVitaeManagment.CurriculumVitae;
import com.zidani.gestioncv.personManagment.PersonRequest;
import com.zidani.gestioncv.personManagment.PersonResponse;
import com.zidani.gestioncv.personManagment.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
    @GetMapping("/search/firstName/{firstName}")
    public ResponseEntity<List<PersonResponse>> searchPersonByFirstName(@PathVariable String firstName) {
        List<PersonResponse> persons = personService.searchPersonByFirstName(firstName);
        return ResponseEntity.ok(persons);
    }

    @Operation(
            summary = "Search persons by last name",
            description = "Endpoint to search persons by their last name."
    )
    @ApiResponse(responseCode = "200", description = "Persons retrieved successfully")
    @GetMapping("/search/lastName/{lastName}")
    public ResponseEntity<List<PersonResponse>> searchPersonByLastName(@PathVariable String lastName) {
        List<PersonResponse> persons = personService.searchPersonByLastName(lastName);
        return ResponseEntity.ok(persons);
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
    public ResponseEntity<PersonResponse> updatePersonDetails(@RequestBody PersonRequest updatedPerson) {
        PersonResponse updatedResponse = personService.updatePersonDetails(updatedPerson);
        return ResponseEntity.ok(updatedResponse);
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
}
