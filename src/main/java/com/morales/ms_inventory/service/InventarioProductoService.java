package com.morales.ms_inventory.service;

import com.morales.ms_inventory.models.InventarioProducto;

import java.util.List;

public interface InventarioProductoService {

    List<InventarioProducto> getInventarioProductos();

    InventarioProducto getInventarioProductoById(Long id);

    InventarioProducto getInventarioProductoPorProductoId(Long productId);

    InventarioProducto createInventarioProducto(InventarioProducto inventarioProducto);

    InventarioProducto updateInventarioProducto(InventarioProducto inventarioProducto, Long id);

    InventarioProducto updateInventarioProductoPorProductoId(InventarioProducto inventarioProducto, Long productId);

    void softDeleteInventarioProducto(Long id);

    void softDeleteInventarioProductoPorProductoId(Long productId);

}
