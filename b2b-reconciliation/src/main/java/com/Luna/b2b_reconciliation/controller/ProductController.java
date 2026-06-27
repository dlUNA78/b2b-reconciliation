package com.Luna.b2b_reconciliation.controller;

import com.Luna.b2b_reconciliation.service.ProductService;
import com.Luna.b2b_reconciliation.dto.ProductDTO;
import com.Luna.b2b_reconciliation.model.Product;
import com.Luna.b2b_reconciliation.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;



    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {

        // 1. Convertir el DTO (Mensajero) a Entidad (Trabajador de BD)
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setSku(productDTO.getSku());
        product.setPrice(productDTO.getPrice());
        product.setSystemStock(productDTO.getSystemStock());

        // 2. Guardar la Entidad en la Base de Datos
        Product savedProduct = productRepository.save(product);

        // 3. Convertir la Entidad guardada de vuelta a un DTO para responder de forma segura
        ProductDTO responseDTO = new ProductDTO();
        responseDTO.setId(savedProduct.getId());
        responseDTO.setName(savedProduct.getName());
        responseDTO.setSku(savedProduct.getSku());
        responseDTO.setPrice(savedProduct.getPrice());
        responseDTO.setSystemStock(savedProduct.getSystemStock());

        return responseDTO;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/{id}/reconcile")
    public String reconcileProduct(
            @PathVariable Long id,
            @RequestParam Integer physicalStock) {

        // El Controller solo pasa los parámetros al Service y devuelve la respuesta
        return productService.reconcileStock(id, physicalStock);
    }
}