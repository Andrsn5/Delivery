package dev.andrsn.delivery.common.dto;

import dev.andrsn.delivery.common.model.ProductType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ответ с информацией о продукте")
public class ProductResponse {

    @Schema(description = "Интеграционный ID продукта", example = "110e3900-e29b-41d4-a716-116655220000")
    private String integrUUID;

    @Schema(description = "Номер товара на складе", example = "ITEM-001")
    private String itemNumber;

    @Schema(description = "Тип продукта", example = "BOOK")
    private ProductType type;

    @Schema(description = "Кастомный текст", example = "С ДР!")
    private String customContext;

    @Schema(description = "Дата создания", example = "2026-04-28T10:30:00")
    private LocalDateTime createdAt;
}
