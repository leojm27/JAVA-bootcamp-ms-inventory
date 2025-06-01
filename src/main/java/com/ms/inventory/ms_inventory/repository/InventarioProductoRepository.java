package com.ms.inventory.ms_inventory.repository;

import com.ms.inventory.ms_inventory.models.InventarioProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioProductoRepository extends JpaRepository<InventarioProducto, Long> {
    InventarioProducto findByProductoId(Long productId);
}
