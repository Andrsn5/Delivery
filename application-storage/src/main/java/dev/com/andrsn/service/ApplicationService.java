package dev.com.andrsn.service;

import dev.andrsn.delivery.common.dto.ApplicationResponse;
import dev.andrsn.delivery.common.dto.CreateApplicationRequest;
import dev.andrsn.delivery.common.dto.ProductResponse;
import dev.andrsn.delivery.common.dto.UpdateApplicationStatusRequest;
import dev.andrsn.delivery.common.model.ApplicationStatus;
import dev.com.andrsn.entity.Application;
import dev.com.andrsn.entity.Product;
import dev.com.andrsn.repository.ApplicationRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationService {
    private final ApplicationRepo requestRepository;

    public ApplicationResponse createNewRequest(CreateApplicationRequest creationData) {
        var newRequest = Application.builder()
            .clientId(creationData.getClientId())
            .status(ApplicationStatus.CREATED)
            .build();
        
        var newItem = Product.builder()
            .type(creationData.getProductType())
            .customContext(creationData.getCustomText())
            .build();
        
        newRequest.getProducts().add(newItem);
        var savedRequest = requestRepository.save(newRequest);
        log.info("Created new application", savedRequest.getIntegrUUID());
        
        return mapToResponse(savedRequest);

    }
    
    @Transactional(readOnly = true)
    public List<ApplicationResponse> getRequestsByUser(Long customerIdentifier) {
        var requests = requestRepository.findByClientId(customerIdentifier);
        return requests.stream()
            .map(this::mapToResponse).toList();
    }
    
    @Transactional(readOnly = true)
    public Optional<ApplicationResponse> getByExternalRef(String externalRef) {
        return requestRepository.findByIntegrUUID(externalRef)
            .map(this::mapToResponse);
    }
    public Optional<ApplicationResponse> modifyRequestPhase(String externalRef, UpdateApplicationStatusRequest phaseUpdate) {
        return requestRepository.findByIntegrUUID(externalRef)
            .map(existingRequest -> {
                existingRequest.setStatus(phaseUpdate.getStatus());
                var updated = requestRepository.save(existingRequest);
                log.info("Updated status application", externalRef, phaseUpdate.getStatus());
                return mapToResponse(updated); });
    }
    public List<ApplicationResponse> getRequestsInPhase(ApplicationStatus currentPhase) {
        var requests = requestRepository.findByStatus(currentPhase);
        return requests.stream()
            .map(this::mapToResponse).toList();
    }
    
    private ApplicationResponse mapToResponse(Application source) {
        var productResponses = source.getProducts().stream()
            .map(item -> ProductResponse.builder()
                .integrUUID(item.getIntegrUUID())
                .itemNumber(item.getItemNumber())
                .type(item.getType())
                .customContext(item.getCustomContext())
                .createdAt(item.getCreatedAt())
                .build()).toList();


        return ApplicationResponse.builder()
            .integrUUID(source.getIntegrUUID())
            .status(source.getStatus())
            .clientId(source.getClientId())
            .products(productResponses)
            .createdAt(source.getCreatedAt())
            .build();
    }
}
