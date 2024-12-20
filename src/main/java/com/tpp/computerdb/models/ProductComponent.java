package com.tpp.computerdb.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ProductComponents")
public class ProductComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productComponentId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "component_id")
    private Component component;

    @Column(nullable = false)
    private Integer quantity;

    // Getters and Setters
    public Integer getProductComponentId() {
        return productComponentId;
    }

    public void setProductComponentId(Integer productComponentId) {
        this.productComponentId = productComponentId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
