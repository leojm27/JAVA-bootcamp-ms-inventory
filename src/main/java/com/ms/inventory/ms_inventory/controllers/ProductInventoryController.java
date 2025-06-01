package com.ms.inventory.ms_inventory.controllers;

import com.ms.inventory.ms_inventory.models.ProductInventory;
import com.ms.inventory.ms_inventory.service.ProductInventoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ProductInventoryController {

    private final ProductInventoryService productInventoryService;

    @GetMapping("/api/product-inventories")
    public ResponseEntity<?> getProductInventories() {
        try {
            return ResponseEntity.ok(productInventoryService.getProductInventories());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener inventarios de productos: " + e.getMessage());
        }
    }

    @GetMapping("/api/product-inventories/{id}")
    public ResponseEntity<?> getProductInventoryById(@PathVariable Long id) {
        try {
            var productInventory = productInventoryService.getProductInventoryById(id);
            if (productInventory != null) {
                return ResponseEntity.ok(productInventory);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener inventario de producto con ID " + id + ": " + e.getMessage());
        }
    }

    @PostMapping("/api/product-inventories")
    public ResponseEntity<?> createProductInventory(@RequestBody ProductInventory productInventory) {
        try {
            ProductInventory newProductInventory = productInventoryService.createProductInventory(productInventory);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(newProductInventory);
        } catch (IllegalArgumentException ie) {
            return ResponseEntity.badRequest().body(ie.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al intentar crear nuevo inventario de producto");
        }
    }

    @PutMapping("/api/product-inventories/{id}")
    public ResponseEntity<?> updateProductInventory(@RequestBody ProductInventory productInventory, @PathVariable("id") Long id) {
        try {
            ProductInventory updatedProductInventory = productInventoryService.updateProductInventory(productInventory, id);
            if (updatedProductInventory != null) {
                return ResponseEntity.ok(updatedProductInventory);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar inventario de producto con ID " + id + ": " + e.getMessage());
        }
    }

    @DeleteMapping("/api/product-inventories/{id}")
    public ResponseEntity<?> deleteProductInventory(@PathVariable("id") Long id) {
        try {
            productInventoryService.softDeleteProductInventory(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ie) {
            return ResponseEntity.badRequest().body(ie.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar inventario de producto con ID " + id + ": " + e.getMessage());
        }
    }

}
