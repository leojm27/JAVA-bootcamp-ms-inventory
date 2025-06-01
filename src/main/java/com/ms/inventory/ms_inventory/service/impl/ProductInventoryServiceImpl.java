package com.ms.inventory.ms_inventory.service.impl;

import com.ms.inventory.ms_inventory.models.ProductInventory;
import com.ms.inventory.ms_inventory.repository.ProductInventoryRepository;
import com.ms.inventory.ms_inventory.service.ProductInventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public ProductInventory updateProductInventoryPorProductId(ProductInventory updateProductInventory, Long productId) {
        ProductInventory productInventory = productInventoryRepository.findByProductId(productId);
        if (productInventory == null || productInventory.getDeletedAt() != null) { return null; }

        if (updateProductInventory.getCantidad() != null) {
            productInventory.setCantidad(updateProductInventory.getCantidad());
        }
        if (updateProductInventory.getCantidadMinima() != null) {
            productInventory.setCantidadMinima(updateProductInventory.getCantidadMinima());
        }
        return productInventoryRepository.save(productInventory);
    }

    @Override
    public void softDeleteProductInventoryByProductId(Long productId) {
        ProductInventory productInventory = productInventoryRepository.findByProductId(productId);
        if (productInventory == null || productInventory.getDeletedAt() != null) {
            throw new IllegalArgumentException("El inventario del producto con ID " + productId + " no existe");
        }

        productInventory.setDeletedAt(new Date());
        productInventoryRepository.save(productInventory);
    }
}
