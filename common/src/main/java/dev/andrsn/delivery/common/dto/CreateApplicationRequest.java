package dev.andrsn.delivery.common.dto;

import dev.andrsn.delivery.common.model.ProductType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Запрос на создание заявки")
public class CreateApplicationRequest {
    @Schema(description = "Тип продукта", example = "BOOK", required = true)
    @NotNull(message = "Тип продукта обязателен")
    private ProductType productType;

    @Schema(description = "Тебе на почитать", example = "Потом вернешь!", maxLength = 30)
    @Size(max = 20, message = "Кастомный текст не должен превышать 30 символов")
    private String customText;

    @Schema(description = "ID клиента", example = "1453", required = true)
    @NotNull(message = "ID клиента обязателен")
    private Long clientId;
}
