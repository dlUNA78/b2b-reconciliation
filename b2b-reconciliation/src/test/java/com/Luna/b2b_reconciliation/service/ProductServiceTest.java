package com.Luna.b2b_reconciliation.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Luna.b2b_reconciliation.model.Product;
import com.Luna.b2b_reconciliation.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    // Simulamos la base de datos (No queremos tocar el PostgreSQL real en un test)
    @Mock
    private ProductRepository productRepository;

    // Metemos esa base de datos falsa dentro de nuestro servicio real
    @InjectMocks
    private ProductService productService;

    @Test
    public void testReconcileStock_WhenFaltanteDetectado_ShouldReturnAlert() {

        // ARRANGE (Preparación) ---
        Product mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setName("Laptop Corporativa");
        mockProduct.setSystemStock(50); // El sistema cree que hay 50


        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));




        String result = productService.reconcileStock(1L, 48);



        // Le decimos a Java: "Espero que el resultado sea EXACTAMENTE este texto. Si no, falla la prueba".
        String expectedMessage = " FALTANTE DETECTADO en Laptop Corporativa: Faltan 2 unidades. Stock ajustado a 48.";
        assertEquals(expectedMessage, result);
    }
}