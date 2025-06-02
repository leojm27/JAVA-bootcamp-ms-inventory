package com.morales.ms_inventory;

import com.morales.ms_inventory.controllers.InventarioProductoController;
import com.morales.ms_inventory.models.InventarioProducto;
import com.morales.ms_inventory.service.InventarioProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class InventarioProductoControllerTests {

    private MockMvc mockMvc;

    @Mock
    private InventarioProductoService inventarioProductoService;

    @InjectMocks
    private InventarioProductoController inventarioProductoController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(inventarioProductoController).build();
    }

    /* getInventarioProductos */
    @Test
    void testGetInventarioProductos(){
        when(inventarioProductoService.getInventarioProductos())
            .thenReturn(List.of(new InventarioProducto()));

        ResponseEntity<?> response = inventarioProductoController.getInventarioProductos();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldReturnInternalServerErrorInGetInventarioProductos() {
        when(inventarioProductoService.getInventarioProductos())
            .thenThrow(new RuntimeException("Error al obtener inventarios"));

        ResponseEntity<?> response = inventarioProductoController.getInventarioProductos();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /* getInventarioProductoById */
    @Test
    void testGetInventarioProductoById() {
        Long id = 1L;
        InventarioProducto inventarioProducto = new InventarioProducto();
        when(inventarioProductoService.getInventarioProductoById(id))
            .thenReturn(inventarioProducto);

        ResponseEntity<?> response = inventarioProductoController.getInventarioProductoById(id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(inventarioProducto);
    }

    @Test
    void shouldReturnNullInGetInventarioProductoById(){
        Long id = 1L;
        when(inventarioProductoService.getInventarioProductoById(id))
            .thenReturn(null);

        ResponseEntity<?> response = inventarioProductoController.getInventarioProductoById(id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldReturnInternalServerErrorInGetInventarioProductoById() {
        Long id = 1L;
        when(inventarioProductoService.getInventarioProductoById(id))
            .thenThrow(new RuntimeException("Error al obtener inventario"));

        ResponseEntity<?> response = inventarioProductoController.getInventarioProductoById(id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /* createInventarioProducto */
    @Test
    void testCreateInventarioProducto() {
        InventarioProducto inventarioProducto = new InventarioProducto();
        when(inventarioProductoService.createInventarioProducto(inventarioProducto))
            .thenReturn(inventarioProducto);

        ResponseEntity<?> response = inventarioProductoController.createInventarioProducto(inventarioProducto);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(inventarioProducto);
    }

    @Test
    void shouldReturnIllegalArgumentExceptionInCreateInventarioProducto() {
        InventarioProducto inventarioProducto = new InventarioProducto();
        when(inventarioProductoService.createInventarioProducto(inventarioProducto))
            .thenThrow(new IllegalArgumentException("Error al crear inventario"));

        ResponseEntity<?> response = inventarioProductoController.createInventarioProducto(inventarioProducto);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody()).isEqualTo("Error al crear inventario");
    }

    @Test
    void shouldReturnExceptionInCreateInventarioProducto() {
        InventarioProducto inventarioProducto = new InventarioProducto();
        when(inventarioProductoService.createInventarioProducto(inventarioProducto))
            .thenThrow(new RuntimeException("Error al crear inventario"));

        ResponseEntity<?> response = inventarioProductoController.createInventarioProducto(inventarioProducto);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isEqualTo("Ocurrió un error al intentar crear nuevo inventario de producto");
    }

    /* updateInventarioProducto */
    @Test
    void testUpdateInventarioProducto() {
        Long id = 1L;
        InventarioProducto inventarioProducto = new InventarioProducto();
        when(inventarioProductoService.updateInventarioProducto(inventarioProducto, id))
            .thenReturn(inventarioProducto);

        ResponseEntity<?> response = inventarioProductoController.updateInventarioProducto(inventarioProducto, id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(inventarioProducto);
    }

    @Test
    void shouldReturnNullInUpdateInventarioProducto() {
        Long id = 1L;
        InventarioProducto inventarioProducto = new InventarioProducto();
        when(inventarioProductoService.updateInventarioProducto(inventarioProducto, id))
            .thenReturn(null);

        ResponseEntity<?> response = inventarioProductoController.updateInventarioProducto(inventarioProducto, id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldReturnExceptionInUpdateInventarioProducto() {
        Long id = 1L;
        InventarioProducto inventarioProducto = new InventarioProducto();
        when(inventarioProductoService.updateInventarioProducto(inventarioProducto, id))
            .thenThrow(new RuntimeException("Error al actualizar inventario"));

        ResponseEntity<?> response = inventarioProductoController.updateInventarioProducto(inventarioProducto, id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isEqualTo("Error al actualizar inventario de producto con ID " + id + ": Error al actualizar inventario");
    }

    /* deleteInventarioProducto */
    @Test
    void testDeleteInventarioProducto() {
        Long id = 1L;
        doNothing().when(inventarioProductoService).softDeleteInventarioProducto(id);

        ResponseEntity<?> response = inventarioProductoController.deleteInventarioProducto(id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldReturnIllegalArgumentExceptionInDeleteInventarioProducto() {
        Long id = 1L;
        doThrow(new IllegalArgumentException("Error al eliminar inventario")).when(inventarioProductoService).softDeleteInventarioProducto(id);

        ResponseEntity<?> response = inventarioProductoController.deleteInventarioProducto(id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isEqualTo("Error al eliminar inventario");
    }

    @Test
    void shouldReturnExceptionInDeleteInventarioProducto() {
        Long id = 1L;
        doThrow(new RuntimeException("Error al eliminar inventario")).when(inventarioProductoService).softDeleteInventarioProducto(id);

        ResponseEntity<?> response = inventarioProductoController.deleteInventarioProducto(id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isEqualTo("Error al eliminar inventario de producto con ID " + id + ": Error al eliminar inventario");
    }

    /* getInventarioProductoPorProductoId */
    @Test
    void testGetInventarioProductoPorProductoId() {
        Long productId = 1L;
        InventarioProducto inventarioProducto = new InventarioProducto();
        when(inventarioProductoService.getInventarioProductoPorProductoId(productId))
            .thenReturn(inventarioProducto);

        ResponseEntity<?> response = inventarioProductoController.getInventarioProductoPorProductoId(productId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(inventarioProducto);
    }

    @Test
    void shouldReturnNullInGetInventarioProductoPorProductoId() {
        Long productId = 1L;
        when(inventarioProductoService.getInventarioProductoPorProductoId(productId))
            .thenReturn(null);

        ResponseEntity<?> response = inventarioProductoController.getInventarioProductoPorProductoId(productId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldReturnExceptionInGetInventarioProductoPorProductoId() {
        Long productId = 1L;
        when(inventarioProductoService.getInventarioProductoPorProductoId(productId))
            .thenThrow(new RuntimeException("Error al obtener inventario por producto"));

        ResponseEntity<?> response = inventarioProductoController.getInventarioProductoPorProductoId(productId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /* updateInventarioProductoPorProductoId */
    @Test
    void testUpdateInventarioProductoPorProductoId() {
        Long productId = 1L;
        InventarioProducto inventarioProducto = new InventarioProducto();
        when(inventarioProductoService.updateInventarioProductoPorProductoId(inventarioProducto, productId))
            .thenReturn(inventarioProducto);

        ResponseEntity<?> response = inventarioProductoController.updateInventarioProductoPorProductoId(inventarioProducto, productId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(inventarioProducto);
    }

    @Test
    void shouldReturnNullInUpdateInventarioProductoPorProductoId() {
        Long productId = 1L;
        InventarioProducto inventarioProducto = new InventarioProducto();
        when(inventarioProductoService.updateInventarioProductoPorProductoId(inventarioProducto, productId))
            .thenReturn(null);

        ResponseEntity<?> response = inventarioProductoController.updateInventarioProductoPorProductoId(inventarioProducto, productId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldReturnExceptionInUpdateInventarioProductoPorProductoId() {
        Long productId = 1L;
        InventarioProducto inventarioProducto = new InventarioProducto();
        when(inventarioProductoService.updateInventarioProductoPorProductoId(inventarioProducto, productId))
            .thenThrow(new RuntimeException("Error al actualizar inventario por producto"));

        ResponseEntity<?> response = inventarioProductoController.updateInventarioProductoPorProductoId(inventarioProducto, productId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isEqualTo("Error al actualizar inventario de producto con ID " + productId + ": Error al actualizar inventario por producto");
    }

    /* deleteInventarioProductoPorProductoId */
    @Test
    void testDeleteInventarioProductoPorProductoId() {
        Long productId = 1L;
        doNothing().when(inventarioProductoService).softDeleteInventarioProductoPorProductoId(productId);

        ResponseEntity<?> response = inventarioProductoController.deleteInventarioProductoPorProductoId(productId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    void shouldReturnIllegalArgumentExceptionInDeleteInventarioProductoPorProductoId() {
        Long productId = 1L;
        doThrow(new IllegalArgumentException("Error al eliminar inventario por producto")).when(inventarioProductoService).softDeleteInventarioProductoPorProductoId(productId);

        ResponseEntity<?> response = inventarioProductoController.deleteInventarioProductoPorProductoId(productId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isEqualTo("Error al eliminar inventario por producto");
    }

    @Test
    void shouldReturnExceptionInDeleteInventarioProductoPorProductoId() {
        Long productId = 1L;
        doThrow(new RuntimeException("Error al eliminar inventario por producto")).when(inventarioProductoService).softDeleteInventarioProductoPorProductoId(productId);

        ResponseEntity<?> response = inventarioProductoController.deleteInventarioProductoPorProductoId(productId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isEqualTo("Error al eliminar inventario de producto con ID " + productId + ": Error al eliminar inventario por producto");
    }
}
