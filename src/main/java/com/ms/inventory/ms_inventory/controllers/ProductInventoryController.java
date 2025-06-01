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

    /**
     * Obtiene todos los inventarios de productos.
     * @return ResponseEntity con la lista de inventarios de productos o un mensaje de error.
     */
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

    /**
     * Obtiene un inventario de producto por su ID.
     * @param id
     * @return ResponseEntity con el inventario de producto o un mensaje de error.
     */
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

    /**
     * Crea un nuevo inventario de producto.
     * @param productInventory
     * @return ResponseEntity con el inventario de producto creado o un mensaje de error.
     */
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

    /**
     * Actualiza un inventario de producto existente.
     * @param productInventory
     * @param id
     * @return ResponseEntity con el inventario de producto actualizado o un mensaje de error.
     */
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

    /**
     * Elimina de manera logica un inventario de producto por su ID.
     * @param id
     * @return ResponseEntity con el estado de la eliminación o un mensaje de error.
     */
    @DeleteMapping("/api/product-inventories/{id}")
    public ResponseEntity<?> deleteProductInventory(@PathVariable("id") Long id) {
        try {
            productInventoryService.softDeleteProductInventory(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException ie) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ie.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar inventario de producto con ID " + id + ": " + e.getMessage());
        }
    }

    /**
     * Actualiza un inventario de producto por su ID de producto.
     * @param productInventory
     * @param id
     * @return ResponseEntity con el inventario de producto actualizado o un mensaje de error.
     */
    @PutMapping("/api/product-inventories/by-product/{productId}")
    public ResponseEntity<?> updateProductInventoryPorProductId(@RequestBody ProductInventory productInventory, @PathVariable("productId") Long id) {
        try {
            ProductInventory updatedProductInventory = productInventoryService.updateProductInventoryPorProductId(productInventory, id);
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

    /**
     * Elimina de manera logica un inventario de producto por su ID de producto.
     * @param productId
     * @return ResponseEntity con el estado de la eliminación o un mensaje de error.
     */
    @DeleteMapping("/api/product-inventories/by-product/{productId}")
    public ResponseEntity<?> deleteProductInventoryByProductId(@PathVariable("productId") Long productId) {
        try {
            productInventoryService.softDeleteProductInventoryByProductId(productId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException ie) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ie.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar inventario de producto con ID " + productId + ": " + e.getMessage());
        }
    }

}
