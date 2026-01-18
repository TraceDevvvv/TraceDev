package com.example.insertdelayadmin.service;

import com.example.insertdelayadmin.model.Administrator;
import com.example.insertdelayadmin.repository.AdministratorRepository;
import com.example.insertdelayadmin.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Custom implementation of Spring Security's {@link UserDetailsService}.
 * This service is responsible for loading user-specific data (Administrator details)
 * during the authentication process. It retrieves Administrator information from the
 * database and converts it into a {@link CustomUserDetails} object that Spring Security can use.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AdministratorRepository administratorRepository;

    /**
     * Constructor for CustomUserDetailsService, injecting the AdministratorRepository.
     *
     * @param administratorRepository The repository for accessing Administrator entities.
     */
    @Autowired
    public CustomUserDetailsService(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may be case sensitive, or case insensitive depending on how the implementation
     * instance is configured. In this case, it fetches an {@link Administrator} by username.
     *
     * @param username The username identifying the user whose data is required.
     * @return A fully populated user record (an instance of {@link UserDetails})
     *         if the user exists.
     * @throws UsernameNotFoundException if the user could not be found or has no
     *         granted authorities.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Administrator> administratorOptional = administratorRepository.findByUsername(username);

        if (administratorOptional.isEmpty()) {
            throw new UsernameNotFoundException("Administrator not found with username: " + username);
        }

        Administrator administrator = administratorOptional.get();

        // Convert the Administrator entity to a CustomUserDetails object
        // CustomUserDetails will encapsulate the necessary information for Spring Security
        return new CustomUserDetails(
                administrator.getId(),
                administrator.getUsername(),
                administrator.getPasswordHash(),
                administrator.getRole()
        );
    }
}