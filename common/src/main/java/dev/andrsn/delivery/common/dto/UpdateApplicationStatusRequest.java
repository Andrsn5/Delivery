package dev.andrsn.delivery.common.dto;

import dev.andrsn.delivery.common.model.ApplicationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Запрос на обновление статуса заявки")
public class UpdateApplicationStatusRequest {

    @Schema(description = "Новый статус заявки", example = "RESERVED", required = true)
    @NotNull(message = "Статус обязателен")
    private ApplicationStatus status;
}
