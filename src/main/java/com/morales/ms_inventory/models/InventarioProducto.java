package com.morales.ms_inventory.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Entity
@Table(name = "inventario_producto")
@NoArgsConstructor
@AllArgsConstructor
public class InventarioProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "producto_id", nullable = false)
    private Long productoId;

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

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}
