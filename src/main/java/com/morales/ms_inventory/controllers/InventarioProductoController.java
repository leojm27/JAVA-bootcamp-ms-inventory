package com.morales.ms_inventory.controllers;

import com.morales.ms_inventory.models.InventarioProducto;
import com.morales.ms_inventory.service.InventarioProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class InventarioProductoController {

    private final InventarioProductoService productInventoryService;

    /**
     * Obtiene todos los inventarios de productos.
     * @return ResponseEntity con la lista de inventarios de productos o un mensaje de error.
     */
    @GetMapping("/api/inventario-producto")
    public ResponseEntity<?> getProductInventories() {
        System.out.println("Obteniendo inventarios de productos...");
        try {
            return ResponseEntity.ok(productInventoryService.getInventarioProductos());
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
    @GetMapping("/api/inventario-producto/{id}")
    public ResponseEntity<?> getProductInventoryById(@PathVariable Long id) {
        try {
            var productInventory = productInventoryService.getInventarioProductoById(id);
            if (productInventory != null) {
                return ResponseEntity.ok(productInventory);
            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Inventario de producto con ID " + id + " no encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener inventario de producto con ID " + id + ": " + e.getMessage());
        }
    }

    /**
     * Crea un nuevo inventario de producto.
     * @param inventarioProducto
     * @return ResponseEntity con el inventario de producto creado o un mensaje de error.
     */
    @PostMapping("/api/inventario-producto")
    public ResponseEntity<?> createInventarioProducto(@RequestBody InventarioProducto inventarioProducto) {
        try {
            InventarioProducto newInventarioProducto = productInventoryService.createInventarioProducto(inventarioProducto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(newInventarioProducto);
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
     * @param inventarioProducto
     * @param id
     * @return ResponseEntity con el inventario de producto actualizado o un mensaje de error.
     */
    @PutMapping("/api/inventario-producto/{id}")
    public ResponseEntity<?> updateInventarioProducto(@RequestBody InventarioProducto inventarioProducto, @PathVariable("id") Long id) {
        System.out.println("updateInventarioProducto");
        try {
            InventarioProducto updatedInventarioProducto = productInventoryService.updateInventarioProducto(inventarioProducto, id);
            if (updatedInventarioProducto != null) {
                return ResponseEntity.ok(updatedInventarioProducto);
            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Inventario de producto con ID " + id + " no encontrado");
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
    @DeleteMapping("/api/inventario-producto/{id}")
    public ResponseEntity<?> deleteInventarioProducto(@PathVariable("id") Long id) {
        try {
            productInventoryService.softDeleteInventarioProducto(id);
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
     * @param inventarioProducto
     * @param id
     * @return ResponseEntity con el inventario de producto actualizado o un mensaje de error.
     */
    @PutMapping("/api/inventario-producto/por-producto/{productId}")
    public ResponseEntity<?> updateInventarioProductoPorProductoId(@RequestBody InventarioProducto inventarioProducto, @PathVariable("productId") Long id) {
        try {
            InventarioProducto updatedInventarioProducto = productInventoryService.updateInventarioProductoPorProductoId(inventarioProducto, id);
            if (updatedInventarioProducto != null) {
                return ResponseEntity.ok(updatedInventarioProducto);
            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Inventario de producto con ID " + id + " no encontrado");
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
    @DeleteMapping("/api/inventario-producto/por-producto/{productId}")
    public ResponseEntity<?> deleteInventarioProductoPorProductoId(@PathVariable("productId") Long productId) {
        try {
            productInventoryService.softDeleteInventarioProductoPorProductoId(productId);
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
