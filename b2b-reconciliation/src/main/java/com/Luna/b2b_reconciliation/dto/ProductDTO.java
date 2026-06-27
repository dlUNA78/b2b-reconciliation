package com.Luna.b2b_reconciliation.dto;

public class ProductDTO {

    private Long id;
    private String name;
    private String sku;
    private Double price;
    private Integer systemStock;

    // Constructores
    public ProductDTO() {}

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getSystemStock() { return systemStock; }
    public void setSystemStock(Integer systemStock) { this.systemStock = systemStock; }
}