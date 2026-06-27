package com.Luna.b2b_reconciliation.service;

import com.Luna.b2b_reconciliation.model.Product;
import com.Luna.b2b_reconciliation.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public String reconcileStock(Long productId, Integer physicalStock) {


        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + productId));

        // Calcular la discrepancia
        int systemStock = product.getSystemStock();
        int discrepancy = physicalStock - systemStock;

        // Ajustar el inventario del sistema a la realidad física
        product.setSystemStock(physicalStock);
        productRepository.save(product); // Guardamos la corrección

        // 4Generar el reporte de auditoría
        if (discrepancy == 0) {
            return "Conciliación perfecta para " + product.getName() + ". Sin discrepancias.";
        } else if (discrepancy < 0) {
            return "FALTANTE DETECTADO en " + product.getName() + ": Faltan " + Math.abs(discrepancy) + " unidades. Stock ajustado a " + physicalStock + ".";
        } else {
            return "⚠️ SOBRANTE DETECTADO en " + product.getName() + ": Sobran " + discrepancy + " unidades. Stock ajustado a " + physicalStock + ".";
        }
    }
}
