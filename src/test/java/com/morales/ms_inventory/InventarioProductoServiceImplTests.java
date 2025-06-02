package com.morales.ms_inventory;

import com.morales.ms_inventory.models.InventarioProducto;
import com.morales.ms_inventory.repository.InventarioProductoRepository;
import com.morales.ms_inventory.service.impl.InventarioProductoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


public class InventarioProductoServiceImplTests {

    private MockMvc mockMvc;

    @Mock
    private InventarioProductoRepository inventarioProductoRepository;

    @InjectMocks
    private InventarioProductoServiceImpl inventarioProductoServiceImpl;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(inventarioProductoServiceImpl).build();
    }

    /* getInventarioProductos */
    @Test
    void testGetInventarioProductos(){
        when(inventarioProductoRepository.findAll()).thenReturn(List.of());
        List<InventarioProducto> inventarioProductos = inventarioProductoServiceImpl.getInventarioProductos();
        assertThat(inventarioProductos).isNotNull();
    }

    /* getInventarioProductoById */
    @Test
    void testGetInventarioProductoById(){
        Long id = 1L;
        InventarioProducto inventarioProducto = new InventarioProducto();
        inventarioProducto.setId(id);

        when(inventarioProductoRepository.findById(id)).thenReturn(java.util.Optional.of(inventarioProducto));

        InventarioProducto result = inventarioProductoServiceImpl.getInventarioProductoById(id);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
    }

    /* getInventarioProductoPorProductoId */
    @Test
    void testGetInventarioProductoPorProductoId(){
        Long productId = 1L;
        InventarioProducto inventarioProducto = new InventarioProducto();
        inventarioProducto.setProductoId(productId);

        List<InventarioProducto> inventarioList = List.of(inventarioProducto);
        when(inventarioProductoRepository.findAll()).thenReturn(inventarioList);

        InventarioProducto result = inventarioProductoServiceImpl.getInventarioProductoPorProductoId(productId);
        assertThat(result).isNotNull();
        assertThat(result.getProductoId()).isEqualTo(productId);
    }

    @Test
    void shouldReturnIllegalArgumentExceptionInGetInventarioProductoPorProductoId(){
        Long productId = 1L;

        when(inventarioProductoRepository.findByProductoId(productId))
                .thenReturn(null);

        try {
            inventarioProductoServiceImpl.getInventarioProductoPorProductoId(productId);
        } catch (IllegalArgumentException e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
    }

    /* createInventarioProducto */
    @Test
    void testCreateInventarioProducto(){
        InventarioProducto inventarioProducto = new InventarioProducto();
        inventarioProducto.setCantidad(100L);
        inventarioProducto.setProductoId(1L);
        inventarioProducto.setDeletedAt(new Date());

        List<InventarioProducto> inventarioList = List.of(inventarioProducto);
        when(inventarioProductoRepository.findAll()).thenReturn(inventarioList);
        when(inventarioProductoRepository.save(inventarioProducto)).thenReturn(inventarioProducto);

        InventarioProducto result = inventarioProductoServiceImpl.createInventarioProducto(inventarioProducto);
        assertThat(result).isNotNull();
        assertThat(result.getCantidad()).isEqualTo(100);
    }

    /* updateInventarioProducto */
    @Test
    void testUpdateInventarioProducto() {
        Long id = 1L;
        InventarioProducto inventarioProducto = new InventarioProducto();
        inventarioProducto.setId(id);
        inventarioProducto.setCantidad(200L);

        when(inventarioProductoRepository.findById(id)).thenReturn(java.util.Optional.of(inventarioProducto));
        when(inventarioProductoRepository.save(inventarioProducto)).thenReturn(inventarioProducto);

        InventarioProducto result = inventarioProductoServiceImpl.updateInventarioProducto(inventarioProducto, id);
        assertThat(result).isNotNull();
        assertThat(result.getCantidad()).isEqualTo(200);
    }

    @Test
    void testUpdateInventarioProductoConProductoId() {
        Long id = 1L;
        InventarioProducto inventarioProducto = new InventarioProducto();
        inventarioProducto.setId(id);
        inventarioProducto.setProductoId(2L);

        when(inventarioProductoRepository.findById(id)).thenReturn(java.util.Optional.of(inventarioProducto));
        when(inventarioProductoRepository.save(inventarioProducto)).thenReturn(inventarioProducto);

        InventarioProducto result = inventarioProductoServiceImpl.updateInventarioProducto(inventarioProducto, id);
        assertThat(result).isNotNull();
        assertThat(result.getProductoId()).isEqualTo(2);
    }

    @Test
    void testUpdateInventarioProductoConCantidadMinima() {
        Long id = 1L;
        InventarioProducto inventarioProducto = new InventarioProducto();
        inventarioProducto.setId(id);
        inventarioProducto.setCantidadMinima(15L);

        when(inventarioProductoRepository.findById(id)).thenReturn(java.util.Optional.of(inventarioProducto));
        when(inventarioProductoRepository.save(inventarioProducto)).thenReturn(inventarioProducto);

        InventarioProducto result = inventarioProductoServiceImpl.updateInventarioProducto(inventarioProducto, id);
        assertThat(result).isNotNull();
        assertThat(result.getCantidadMinima()).isEqualTo(15);
    }

    /* softDeleteInventarioProducto */
    @Test
    void testSoftDeleteInventarioProducto() {
        Long id = 1L;
        InventarioProducto inventarioProducto = new InventarioProducto();
        inventarioProducto.setId(id);

        when(inventarioProductoRepository.findById(id)).thenReturn(java.util.Optional.of(inventarioProducto));

        inventarioProductoServiceImpl.softDeleteInventarioProducto(id);

        assertThat(inventarioProducto.getDeletedAt()).isNotNull();
    }

    @Test
    void shouldReturnIllegalArgumentExceptionInSoftDeleteInventarioProducto() {
        Long id = 1L;

        when(inventarioProductoRepository.findById(id)).thenReturn(java.util.Optional.empty());

        try {
            inventarioProductoServiceImpl.softDeleteInventarioProducto(id);
        } catch (IllegalArgumentException e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
    }

    /* updateInventarioProductoPorProductoId */
    @Test
    void testUpdateInventarioProductoPorProductoId() {
        Long productId = 1L;
        InventarioProducto inventarioProducto = new InventarioProducto();
        inventarioProducto.setProductoId(productId);
        inventarioProducto.setCantidad(150L);

        List<InventarioProducto> inventarioList = List.of(inventarioProducto);
        when(inventarioProductoRepository.findAll()).thenReturn(inventarioList);
        when(inventarioProductoRepository.save(inventarioProducto)).thenReturn(inventarioProducto);

        InventarioProducto result = inventarioProductoServiceImpl.updateInventarioProductoPorProductoId(inventarioProducto, productId);
        assertThat(result).isNotNull();
        assertThat(result.getCantidad()).isEqualTo(150);
    }

    /* softDeleteInventarioProductoPorProductoId */
    @Test
    void testSoftDeleteInventarioProductoPorProductoId() {
        Long productId = 1L;
        InventarioProducto inventarioProducto = new InventarioProducto();
        inventarioProducto.setProductoId(productId);

        List<InventarioProducto> inventarioList = List.of(inventarioProducto);
        when(inventarioProductoRepository.findAll()).thenReturn(inventarioList);
        inventarioProductoServiceImpl.softDeleteInventarioProductoPorProductoId(productId);
        assertThat(inventarioProducto.getDeletedAt()).isNotNull();
    }

    @Test
    void shouldReturnIllegalArgumentExceptionInSoftDeleteInventarioProductoPorProductoId() {
        Long productId = 1L;

        when(inventarioProductoRepository.findByProductoId(productId)).thenReturn(null);

        try {
            inventarioProductoServiceImpl.softDeleteInventarioProductoPorProductoId(productId);
        } catch (IllegalArgumentException e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
    }
}
