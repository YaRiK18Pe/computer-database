package com.tpp.computerdb.service;

import com.tpp.computerdb.models.ProductComponent;
import com.tpp.computerdb.repository.ProductComponentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductComponentService {

    @Autowired
    private ProductComponentsRepository productComponentsRepository;

    public List<ProductComponent> getAllProductComponents() {
        return productComponentsRepository.findAll();
    }

    public Optional<ProductComponent> findById(Integer id) {
        return productComponentsRepository.findById(id);
    }

    public Optional<ProductComponent> findByProductIdAndComponentId(Integer productId, Integer componentId) {
        return productComponentsRepository.findByProduct_ProductIdAndComponent_ComponentId(productId, componentId);
    }

    public void saveProductComponent(ProductComponent productComponent) {
        productComponentsRepository.save(productComponent);
    }

    public void updateProductComponent(ProductComponent updatedProductComponent) {
        productComponentsRepository.save(updatedProductComponent);
    }

    @Transactional
    public void deleteById(Integer id) {
        productComponentsRepository.deleteById(id);
    }

    @Transactional
    public void deleteProductComponentById(Integer productId, Integer componentId) {
        productComponentsRepository.deleteByProduct_ProductIdAndComponent_ComponentId(productId, componentId);
    }
}