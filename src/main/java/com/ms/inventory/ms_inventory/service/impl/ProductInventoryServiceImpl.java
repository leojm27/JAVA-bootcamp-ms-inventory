package com.ms.inventory.ms_inventory.service.impl;

import com.ms.inventory.ms_inventory.models.ProductInventory;
import com.ms.inventory.ms_inventory.repository.ProductInventoryRepository;
import com.ms.inventory.ms_inventory.service.ProductInventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductInventoryServiceImpl implements ProductInventoryService {

    private final ProductInventoryRepository productInventoryRepository;

    @Override
    public List<ProductInventory> getProductInventories() {
        return productInventoryRepository.findAll()
                .stream()
                .filter(productInventory -> productInventory.getDeletedAt() == null)
                .toList();
    }

    @Override
    public ProductInventory getProductInventoryById(Long id) {
        return productInventoryRepository.findById(id)
                .filter(productInventory -> productInventory.getDeletedAt() == null)
                .orElse(null);
    }

    @Override
    public ProductInventory createProductInventory(ProductInventory productInventory) {
        return productInventoryRepository.save(productInventory);
    }

    @Override
    public ProductInventory updateProductInventory(ProductInventory productInventory, Long id) {
        return productInventoryRepository.findById(id)
                .map(existingProductInventory -> {
                    if (productInventory.getCantidad() != null) {
                        existingProductInventory.setCantidad(productInventory.getCantidad());
                    }
                    if (productInventory.getProductId() != null) {
                        existingProductInventory.setProductId(productInventory.getProductId());
                    }
                    if (productInventory.getCantidadMinima() != null) {
                        existingProductInventory.setCantidadMinima(productInventory.getCantidadMinima());
                    }
                    return productInventoryRepository.save(existingProductInventory);
                }).orElse(null);
    }

    @Override
    public void softDeleteProductInventory(Long id) {
        ProductInventory productInventory = productInventoryRepository.findById(id).orElse(null);
        if (productInventory == null) {
            throw new IllegalArgumentException("El inventario del producto con ID " + id + " no existe");
        }

        productInventory.setDeletedAt(new java.util.Date());
        productInventoryRepository.save(productInventory);
    }
}
