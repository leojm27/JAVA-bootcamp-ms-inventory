package com.ms.inventory.ms_inventory.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Entity
@Table(name = "product_inventory")
@NoArgsConstructor
public class ProductInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "cantidad", nullable = false)
    private Long cantidad;

    @Column(name = "cantidad_minima", nullable = false)
    private Long cantidadMinima;

    @Column(name = "created_at" , nullable = false)
    private Date createdAt = new Date();

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    public ProductInventory(Long productId, Long cantidad, Long cantidadMinima) {
        this.productId = productId;
        this.cantidad = cantidad;
        this.cantidadMinima = cantidadMinima;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}
