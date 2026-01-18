package com.example.insertdelayadmin.controller;

import com.example.insertdelayadmin.exception.AuthenticationException;
import com.example.insertdelayadmin.exception.InvalidInputException;
import com.example.insertdelayadmin.exception.ResourceNotFoundException;
import com.example.insertdelayadmin.model.DelayEntryRequest;
import com.example.insertdelayadmin.model.DelayRecordDTO;
import com.example.insertdelayadmin.model.LoginRequest;
import com.example.insertdelayadmin.model.LoginResponse;
import com.example.insertdelayadmin.security.CustomUserDetails;
import com.example.insertdelayadmin.service.AuthService;
import com.example.insertdelayadmin.service.DelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * REST Controller for handling administrator-related operations.
 * This includes authentication, creating delay records, and retrieving delay records.
 */
@RestController
@RequestMapping("/api")
public class AdminController {

    private final AuthService authService;
    private final DelayService delayService;

    /**
     * Constructor for AdminController, injecting required serv.
     *
     * @param authService Service for authentication operations.
     * @param delayService Service for managing delay records.
     */
    @Autowired
    public AdminController(AuthService authService, DelayService delayService) {
        this.authService = authService;
        this.delayService = delayService;
    }

    /**
     * Handles administrator login requests.
     * Authenticates the administrator and returns a JWT token upon successful login.
     *
     * @param request The {@link LoginRequest} containing the administrator's username and password.
     * @return A {@link ResponseEntity} containing a {@link LoginResponse} with the JWT token,
     *         username, and role if authentication is successful, or an error status.
     */
    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            // Return 401 Unauthorized for authentication failures
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * Creates a new delay record for a student.
     * This endpoint requires administrator authentication. The administrator's ID is extracted
     * from the security context via {@code @AuthenticationPrincipal}.
     *
     * @param request The {@link DelayEntryRequest} containing student ID, date, and reason for the delay.
     * @param adminDetails The details of the authenticated administrator, provided by Spring Security.
     * @return A {@link ResponseEntity} containing the created {@link DelayRecordDTO} if successful,
     *         or an error status if the student is not found or the input is invalid.
     */
    @PostMapping("/delays")
    public ResponseEntity<DelayRecordDTO> createDelay(@RequestBody DelayEntryRequest request,
                                                      @AuthenticationPrincipal CustomUserDetails adminDetails) {
        try {
            // The adminDetails object contains the ID of the currently authenticated administrator.
            DelayRecordDTO createdDelay = delayService.createDelayRecord(request, adminDetails.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDelay);
        } catch (ResourceNotFoundException e) {
            // Return 404 Not Found if the student or administrator ID is invalid
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InvalidInputException e) {
            // Return 400 Bad Request for invalid delay reasons or other bad input
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Retrieves a list of delay records for a specific date, entered by the authenticated administrator.
     * This endpoint requires administrator authentication.
     *
     * @param date The date for which to retrieve delay records, provided as a query parameter (e.g., ?date=2023-10-26).
     * @param adminDetails The details of the authenticated administrator, provided by Spring Security.
     * @return A {@link ResponseEntity} containing a list of {@link DelayRecordDTO} objects for the specified date
     *         and administrator, or an empty list if no records are found.
     */
    @GetMapping("/delays")
    public ResponseEntity<List<DelayRecordDTO>> getDelaysByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @AuthenticationPrincipal CustomUserDetails adminDetails) {
        // The adminDetails object contains the ID of the currently authenticated administrator.
        List<DelayRecordDTO> delayRecords = delayService.getDelayRecordsForDate(date, adminDetails.getId());
        return ResponseEntity.ok(delayRecords);
    }
}