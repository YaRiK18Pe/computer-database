package com.tpp.computerdb.controllers;

import com.tpp.computerdb.models.ProductComponent;
import com.tpp.computerdb.service.ProductComponentService;
import com.tpp.computerdb.service.ProductService;
import com.tpp.computerdb.service.ComponentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productcomponents")
public class ProductComponentController {

    @Autowired
    private ProductComponentService productComponentService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ComponentService componentService;

    @GetMapping
    public String listProductComponents(Model model) {
        model.addAttribute("productComponents", productComponentService.getAllProductComponents());
        return "productcomponents";
    }

    @GetMapping("/add")
    public String addProductComponentForm(Model model) {
        model.addAttribute("productComponent", new ProductComponent());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("components", componentService.getAllComponents());
        return "add-product-component";
    }

    @PostMapping("/add")
    public String addProductComponent(@Valid @ModelAttribute("productComponent") ProductComponent productComponent, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("products", productService.getAllProducts());
            model.addAttribute("components", componentService.getAllComponents());
            return "add-product-component";
        }
        productComponentService.saveProductComponent(productComponent);
        return "redirect:/productcomponents";
    }

    @GetMapping("/edit/{id}")
    public String editProductComponentForm(@PathVariable("id") Integer id, Model model) {
        ProductComponent productComponent = productComponentService.findById(id)
            .orElse(null);
        
        if (productComponent == null) {
            return "redirect:/productcomponents";
        }
        
        model.addAttribute("productComponent", productComponent);
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("components", componentService.getAllComponents());
        return "edit-product-component";
    }

    @PostMapping("/update")
    public String updateProductComponent(@Valid @ModelAttribute("productComponent") ProductComponent productComponent, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("products", productService.getAllProducts());
            model.addAttribute("components", componentService.getAllComponents());
            return "edit-product-component";
        }
        productComponentService.updateProductComponent(productComponent);
        return "redirect:/productcomponents";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductComponent(@PathVariable("id") Integer id) {
        productComponentService.deleteById(id);
        return "redirect:/productcomponents";
    }
}