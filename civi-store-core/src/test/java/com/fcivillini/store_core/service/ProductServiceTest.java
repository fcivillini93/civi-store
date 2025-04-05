package com.fcivillini.store_core.service;

import com.fcivillini.store_core.mapper.ProductMapper;
import com.fcivillini.store_core.model.Product;
import com.fcivillini.store_core.service.impl.ProductServiceImpl;
import com.fcivillini.store_interface.exc.StoreException;
import com.fcivillini.store_repository.dao.ProductDao;
import com.fcivillini.store_repository.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        Mockito.clearInvocations(productRepository, productMapper);
    }

    @Test
    void testSave() {
        Product product = new Product().setName("Laptop");
        ProductDao productDao = new ProductDao().setName("Laptop");
        ProductDao savedDao = new ProductDao().setId(1L);
        Product savedProduct = new Product().setId(1L);

        when(productMapper.toDao(product)).thenReturn(productDao);
        when(productRepository.save(productDao)).thenReturn(savedDao);
        when(productMapper.fromDao(savedDao)).thenReturn(savedProduct);

        Product result = productService.save(product);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(productMapper).toDao(product);
        verify(productRepository).save(productDao);
        verify(productMapper).fromDao(savedDao);
    }

    @Test
    void testUpdate_Success() throws StoreException {
        Product product = new Product().setId(1L).setName("Laptop Updated");
        ProductDao productDao = new ProductDao().setId(1L);
        ProductDao savedDao = new ProductDao().setId(1L);
        Product updated = new Product().setId(1L).setName("Laptop Updated");

        when(productRepository.findById(1L)).thenReturn(Optional.of(productDao));
        when(productMapper.toDao(product)).thenReturn(productDao);
        when(productRepository.save(productDao)).thenReturn(savedDao);
        when(productMapper.fromDao(savedDao)).thenReturn(updated);

        Product result = productService.update(product);

        assertEquals("Laptop Updated", result.getName());
        verify(productRepository).findById(1L);
        verify(productMapper).toDao(product);
        verify(productRepository).save(productDao);
        verify(productMapper).fromDao(savedDao);
    }

    @Test
    void testUpdate_NotFound() {
        Product product = new Product().setId(99L);

        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        StoreException exception = assertThrows(StoreException.class, () -> productService.update(product));

        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        verify(productRepository).findById(99L);
        verify(productMapper, never()).toDao(any());
        verify(productRepository, never()).save(any());
    }

    @Test
    void testFindById_Success() throws StoreException {
        ProductDao dao = new ProductDao().setId(1L);
        Product product = new Product().setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(dao));
        when(productMapper.fromDao(dao)).thenReturn(product);

        Product result = productService.findById(1L);

        assertEquals(1L, result.getId());
        verify(productRepository).findById(1L);
        verify(productMapper).fromDao(dao);
    }

    @Test
    void testFindById_NotFound() {
        when(productRepository.findById(2L)).thenReturn(Optional.empty());

        StoreException exception = assertThrows(StoreException.class, () -> productService.findById(2L));

        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        verify(productRepository).findById(2L);
        verify(productMapper, never()).fromDao(any());
    }

    @Test
    void testFindAll() {
        ProductDao dao = new ProductDao().setName("Test");
        Product product = new Product().setName("Test");

        when(productRepository.findAll()).thenReturn(List.of(dao));
        when(productMapper.fromDao(dao)).thenReturn(product);

        List<Product> result = productService.findAll();

        assertEquals(1, result.size());
        assertEquals("Test", result.get(0).getName());
        verify(productRepository).findAll();
        verify(productMapper).fromDao(dao);
    }

    @Test
    void testDeleteById() {
        Long id = 5L;

        productService.deleteById(id);

        verify(productRepository).deleteById(id);
    }
}
