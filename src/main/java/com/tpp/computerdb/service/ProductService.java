package com.tpp.computerdb.service;

import com.tpp.computerdb.models.Product;
import com.tpp.computerdb.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductsRepository productsRepository;

    public List<Product> getAllProducts() {
        return productsRepository.findAll(); // Отримати всі продукти
    }

    public Optional<Product> findProductById(Integer id) {
        return productsRepository.findById(id); // Знайти продукт за id
    }

    public void saveProduct(Product product) {
        productsRepository.save(product); // Зберегти новий продукт
    }

    public void updateProduct(Product updatedProduct) {
        Optional<Product> existingProductOpt = productsRepository.findById(updatedProduct.getProductId());
        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setModel(updatedProduct.getModel()); // Оновити модель
            productsRepository.save(existingProduct); // Зберегти оновлений продукт
        }
    }

    public void deleteProductById(Integer id) {
        productsRepository.deleteById(id); // Видалити продукт за id
    }
}
