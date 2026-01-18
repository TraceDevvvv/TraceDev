package com.example.app.infrastructure;

import com.example.app.Infrastructure;
import com.example.app.domain.ClassRegister;

/**
 * Interface for repository operations related to ClassRegister.
 */
public interface IRegisterRepository extends Infrastructure {
 
    ClassRegister findById(String registerId);
}