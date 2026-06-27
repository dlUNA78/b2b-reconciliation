package com.Luna.b2b_reconciliation.service;

import com.Luna.b2b_reconciliation.model.Product;
import com.Luna.b2b_reconciliation.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Esta es nuestra Lógica de Negocio pura
    public String reconcileStock(Long productId, Integer physicalStock) {

        // 1. Buscar el producto en la BD (Si no existe, lanzamos error)
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + productId));

        // 2. Calcular la discrepancia
        int systemStock = product.getSystemStock();
        int discrepancy = physicalStock - systemStock;

        // 3. Ajustar el inventario del sistema a la realidad física
        product.setSystemStock(physicalStock);
        productRepository.save(product); // Guardamos la corrección

        // 4. Generar el reporte de auditoría
        if (discrepancy == 0) {
            return "✅ Conciliación perfecta para " + product.getName() + ". Sin discrepancias.";
        } else if (discrepancy < 0) {
            return "🚨 FALTANTE DETECTADO en " + product.getName() + ": Faltan " + Math.abs(discrepancy) + " unidades. Stock ajustado a " + physicalStock + ".";
        } else {
            return "⚠️ SOBRANTE DETECTADO en " + product.getName() + ": Sobran " + discrepancy + " unidades. Stock ajustado a " + physicalStock + ".";
        }
    }
}
