package com.tpp.computerdb.controllers;

import com.tpp.computerdb.models.Product;
import com.tpp.computerdb.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products"; // Вивести список продуктів
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product"; // Форма додавання продукту
    }

    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "add-product"; // Якщо є помилки, повернути на форму додавання
        }
        productService.saveProduct(product);
        return "redirect:/products"; // Переадресація на список продуктів
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable("id") Integer id, Model model) {
        Product product = productService.findProductById(id).orElse(null);
        if (product != null) {
            model.addAttribute("product", product);
            return "edit-product"; // Форма редагування продукту
        }
        return "redirect:/products"; // Якщо продукт не знайдено, повернути на список
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Integer id, @Valid @ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-product"; // Якщо є помилки, повернути на форму редагування
        }
        product.setProductId(id); // Оновлюємо id продукту
        productService.updateProduct(product);
        return "redirect:/products"; // Переадресація на список продуктів
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProductById(id); // Видалити продукт
        return "redirect:/products"; // Переадресація на список продуктів
    }
}
