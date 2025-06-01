package com.ms.inventory.ms_inventory.repository;

import com.ms.inventory.ms_inventory.models.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Long> {
}
