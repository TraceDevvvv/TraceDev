package com.example.app.infrastructure;

import com.example.app.Infrastructure;
import com.example.app.domain.Justification;
import com.example.app.domain.JustificationStatus;
import java.util.List;

/**
 * Interface for repository operations related to Justification.
 */
public interface IJustificationRepository extends Infrastructure {
  
    List<Justification> findByRegisterIdAndDate(String registerId, String date);

  
    Justification save(Justification justification);

 
    void updateStatus(String justificationId, JustificationStatus status);
}