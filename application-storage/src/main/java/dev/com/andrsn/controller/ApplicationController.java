package dev.com.andrsn.controller;

import dev.andrsn.delivery.common.dto.ApplicationResponse;
import dev.andrsn.delivery.common.dto.CreateApplicationRequest;
import dev.andrsn.delivery.common.dto.UpdateApplicationStatusRequest;
import dev.andrsn.delivery.common.model.ApplicationStatus;
import dev.com.andrsn.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/requests")
@RequiredArgsConstructor
@Tag(name = "Управление заявками", description = "API для создания и управления заявками на товары")
public class ApplicationController {
    
    private final ApplicationService requestManagementService;
    
    @PostMapping
    @Operation(summary = "Создать новую заявку", description = "Создает новую заявку на товар с указанными параметрами")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Заявка успешно создана",
            content = @Content(schema = @Schema(implementation = ApplicationResponse.class))),
        @ApiResponse(responseCode = "400", description = "Некорректные данные запроса"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    public ResponseEntity<ApplicationResponse> createRequest(
            @Valid @RequestBody CreateApplicationRequest requestData) {
        
        log.info("Received request to create application", requestData.getClientId());
        var createdRequest = requestManagementService.createNewRequest(requestData);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
    }
    
    @GetMapping("/user/{userId}")
    @Operation(summary = "Получить заявки клиента", description = "Возвращает все заявки указанного клиента")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Список заявок получен"),
        @ApiResponse(responseCode = "404", description = "Клиент не найден")
    })
    public ResponseEntity<List<ApplicationResponse>> getUserApplications(
            @Parameter(description = "ID клиента", required = true)
            @PathVariable Long userId) {
        
        var userRequests = requestManagementService.getRequestsByUser(userId);
        return ResponseEntity.ok(userRequests);
    }
    
    @GetMapping("/{externalRef}")
    @Operation(summary = "Получить заявку по ID", description = "Возвращает заявку по интеграционному ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Заявка найдена"),
        @ApiResponse(responseCode = "404", description = "Заявка не найдена")
    })
    public ResponseEntity<ApplicationResponse> getApplicationByRef(
            @Parameter(description = "Интеграционный ID заявки", required = true)
            @PathVariable String externalRef) {
        
        return requestManagementService.getByExternalRef(externalRef)
            .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PatchMapping("/{externalRef}/status")
    @Operation(summary = "Обновить статус заявки", description = "Изменяет текущий статус заявки")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Статус обновлен"),
        @ApiResponse(responseCode = "404", description = "Заявка не найдена"),
        @ApiResponse(responseCode = "400", description = "Некорректный статус")
    })
    public ResponseEntity<ApplicationResponse> updateApplicationStatus(
            @Parameter(description = "Интеграционный ID заявки", required = true)
            @PathVariable String externalRef,
            @Valid @RequestBody UpdateApplicationStatusRequest statusData) {


        return requestManagementService.modifyRequestPhase(externalRef, statusData)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/status/{currentPhase}")
    @Operation(summary = "Получить заявки по статусу", description = "Возвращает все заявки с указанным статусом")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Список заявок получен")
    })
    public ResponseEntity<List<ApplicationResponse>> getApplicationsByStatus(
            @Parameter(description = "Статус заявки", required = true)
            @PathVariable ApplicationStatus currentPhase) {
        var filteredRequests = requestManagementService.getRequestsInPhase(currentPhase);
        return ResponseEntity.ok(filteredRequests);
    }
}
