package dev.andrsn.delivery.common.dto;

import dev.andrsn.delivery.common.model.ApplicationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ответ с информацией о заявке")
public class ApplicationResponse {
    @Schema(description = "Интеграционный ID заявки", example = "550e8400-e29b-41d4-a716-446655440000")
    private String integrUUID;


    @Schema(description = "ID клиента", example = "12345")
    private Long clientId;

    @Schema(description = "Статус заявки", example = "CREATED")
    private ApplicationStatus status;


    @Schema(description = "Список продуктов в заявке")
    private List<ProductResponse> products;

    @Schema(description = "Дата создания заявки", example = "2025-04-28T10:30:00")
    private LocalDateTime createdAt;
}
