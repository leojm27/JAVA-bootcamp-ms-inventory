package com.ms.inventory.ms_inventory.service;

import com.ms.inventory.ms_inventory.models.ProductInventory;

import java.util.List;

public interface ProductInventoryService {

    List<ProductInventory> getProductInventories();

    ProductInventory getProductInventoryById(Long id);

    ProductInventory createProductInventory(ProductInventory productInventory);

    ProductInventory updateProductInventory(ProductInventory productInventory, Long id);

    void softDeleteProductInventory(Long id);

}
