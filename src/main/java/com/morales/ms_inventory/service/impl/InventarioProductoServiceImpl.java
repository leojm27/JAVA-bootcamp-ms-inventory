package com.morales.ms_inventory.service.impl;

import com.morales.ms_inventory.repository.InventarioProductoRepository;
import com.morales.ms_inventory.service.InventarioProductoService;
import com.morales.ms_inventory.models.InventarioProducto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class InventarioProductoServiceImpl implements InventarioProductoService {

    private final InventarioProductoRepository productInventoryRepository;

    /**
     * Obtiene una lista de todos los inventarios de productos.
     * @return List<ProductInventory>
     */
    @Override
    public List<InventarioProducto> getInventarioProductos() {
        return productInventoryRepository.findAll()
                .stream()
                .filter(inventarioProducto -> inventarioProducto.getDeletedAt() == null)
                .toList();
    }

    /**
     * Obtiene un inventario de producto por su ID.
     * @param id
     * @return ProductInventory o null si no se encuentra el inventario.
     */
    @Override
    public InventarioProducto getInventarioProductoById(Long id) {
        return productInventoryRepository.findById(id)
                .filter(inventarioProducto -> inventarioProducto.getDeletedAt() == null)
                .orElse(null);
    }

    /**
     * Obtiene un inventario de producto por su ID de producto.
     * @param productId
     * @return InventarioProducto o null si no se encuentra el inventario.
     */
    @Override
    public InventarioProducto getInventarioProductoPorProductoId(Long productId) {
        InventarioProducto inventarioProducto = productInventoryRepository.findAll()
                .stream()
                .filter(inventario -> inventario.getProductoId().equals(productId) && inventario.getDeletedAt() == null)
                .findFirst()
                .orElse(null);

        if (inventarioProducto == null || inventarioProducto.getDeletedAt() != null) {
            throw new IllegalArgumentException("El inventario del producto con ID " + productId + " no existe");
        }

        return inventarioProducto;
    }

    /**
     * Crea un nuevo inventario de producto.
     * @param inventarioProducto
     * @return ProductInventory creado.
     */
    @Override
    public InventarioProducto createInventarioProducto(InventarioProducto inventarioProducto) {
        InventarioProducto existingInventario = productInventoryRepository.findAll()
                .stream()
                .filter(inv -> inv.getProductoId().equals(inventarioProducto.getProductoId()) && inv.getDeletedAt() == null)
                .findFirst()
                .orElse(null);

        if (existingInventario != null && existingInventario.getDeletedAt() == null) {
            throw new IllegalArgumentException("El inventario del producto con ID " + inventarioProducto.getProductoId() + " ya existe");
        }

        return productInventoryRepository.save(inventarioProducto);
    }

    /**
     * Actualiza un inventario de producto por su ID.
     * @param inventarioProducto
     * @param id
     * @return ProductInventory actualizado o null si no se encuentra el inventario.
     */
    @Override
    public InventarioProducto updateInventarioProducto(InventarioProducto inventarioProducto, Long id) {
        return productInventoryRepository.findById(id).filter(existingInventarioProducto -> existingInventarioProducto.getDeletedAt() == null)
                .map(existingInventarioProducto -> {
                    if (inventarioProducto.getCantidad() != null) {
                        existingInventarioProducto.setCantidad(inventarioProducto.getCantidad());
                    }
                    if (inventarioProducto.getProductoId() != null) {
                        existingInventarioProducto.setProductoId(inventarioProducto.getProductoId());
                    }
                    if (inventarioProducto.getCantidadMinima() != null) {
                        existingInventarioProducto.setCantidadMinima(inventarioProducto.getCantidadMinima());
                    }
                    return productInventoryRepository.save(existingInventarioProducto);
                }).orElse(null);
    }

    /**
     * Elimina de manera logica un inventario de producto por su ID.
     * @param id
     * @throws IllegalArgumentException si el inventario del producto no existe.
     */
    @Override
    public void softDeleteInventarioProducto(Long id) {
        InventarioProducto inventarioProducto = productInventoryRepository.findById(id)
                .filter(inventario -> inventario.getDeletedAt() == null)
                .orElse(null);
        if (inventarioProducto == null) {
            throw new IllegalArgumentException("El inventario del producto con ID " + id + " no existe");
        }

        inventarioProducto.setDeletedAt(new java.util.Date());
        productInventoryRepository.save(inventarioProducto);
    }

    /**
     * Actualiza un inventario de producto por su ID de producto.
     * @param updateInventarioProducto
     * @param productId
     * @return ProductInventory actualizado o null si no se encuentra el inventario.
     */
    @Override
    public InventarioProducto updateInventarioProductoPorProductoId(InventarioProducto updateInventarioProducto, Long productId) {
        InventarioProducto inventarioProducto = productInventoryRepository.findAll()
                .stream()
                .filter(inventario -> inventario.getProductoId().equals(productId) && inventario.getDeletedAt() == null)
                .findFirst()
                .orElse(null);

        if (inventarioProducto == null || inventarioProducto.getDeletedAt() != null) { return null; }

        if (updateInventarioProducto.getCantidad() != null) {
            inventarioProducto.setCantidad(updateInventarioProducto.getCantidad());
        }
        if (updateInventarioProducto.getCantidadMinima() != null) {
            inventarioProducto.setCantidadMinima(updateInventarioProducto.getCantidadMinima());
        }
        return productInventoryRepository.save(inventarioProducto);
    }

    /**
     * Elimina de manera logica un inventario de producto por su ID de producto.
     * @param productId
     * @throws IllegalArgumentException si el inventario del producto no existe o ya ha sido eliminado.
     */
    @Override
    public void softDeleteInventarioProductoPorProductoId(Long productId) {
        InventarioProducto inventarioProducto = productInventoryRepository.findAll()
                .stream()
                .filter(inventario -> inventario.getProductoId().equals(productId) && inventario.getDeletedAt() == null)
                .findFirst()
                .orElse(null);

        if (inventarioProducto == null || inventarioProducto.getDeletedAt() != null) {
            throw new IllegalArgumentException("El inventario del producto con ID " + productId + " no existe");
        }

        inventarioProducto.setDeletedAt(new Date());
        productInventoryRepository.save(inventarioProducto);
    }
}
