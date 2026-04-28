package dev.com.andrsn.repository;

import dev.com.andrsn.entity.Application;
import dev.andrsn.delivery.common.model.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Long> {
    Optional<Application> findByIntegrUUID(String externalRef);
    
    List<Application> findByClientId(Long customerIdentifier);
    
    List<Application> findByStatus(ApplicationStatus currentPhase);
    
        
}
