package dev.com.andrsn.entity;

import dev.andrsn.delivery.common.model.ProductType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "integrUUID", nullable = false, unique = true)
    @Builder.Default
    private String integrUUID = UUID.randomUUID().toString();

    @Column(name = "itemNumber")
    private String itemNumber;

    @Column(name = "customContext", length = 1000)
    private String customContext;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    @CreationTimestamp
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
}
