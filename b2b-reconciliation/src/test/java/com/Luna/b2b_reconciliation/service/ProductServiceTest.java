package com.Luna.b2b_reconciliation.service;

import com.Luna.b2b_reconciliation.model.Product;
import com.Luna.b2b_reconciliation.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

// Importaciones estáticas para usar los métodos de aserción y simulación directamente
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    // 🛡️ MOCK: Simulamos la base de datos (No queremos tocar el PostgreSQL real en un test)
    @Mock
    private ProductRepository productRepository;

    // 💉 INJECT MOCKS: Metemos esa base de datos falsa dentro de nuestro servicio real
    @InjectMocks
    private ProductService productService;

    @Test
    public void testReconcileStock_WhenFaltanteDetectado_ShouldReturnAlert() {

        // --- 1. ARRANGE (Preparación) ---
        Product mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setName("Laptop Corporativa");
        mockProduct.setSystemStock(50); // El sistema cree que hay 50

        // Le enseñamos un "truco" a la BD falsa: "Si te piden el ID 1, devuelve el mockProduct"
        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));


        // --- 2. ACT (Ejecución) ---
        // Ejecutamos tu Lógica de Negocio: Simulamos que el auditor contó 48 físicamente
        String result = productService.reconcileStock(1L, 48);


        // --- 3. ASSERT (Verificación) ---
        // Le decimos a Java: "Espero que el resultado sea EXACTAMENTE este texto. Si no, falla la prueba".
        String expectedMessage = "🚨 FALTANTE DETECTADO en Laptop Corporativa: Faltan 2 unidades. Stock ajustado a 48.";
        assertEquals(expectedMessage, result);
    }
}