package com.tpp.computerdb.controllers;

import com.tpp.computerdb.models.Component;
import com.tpp.computerdb.service.ComponentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/components")
public class ComponentController {

    @Autowired
    private ComponentService componentService;

    @GetMapping
    public String listComponents(Model model) {
        model.addAttribute("components", componentService.getAllComponents());
        return "components";
    }

    @GetMapping("/add")
    public String addComponentForm(Model model) {
        model.addAttribute("component", new Component());
        return "add-component";
    }

    @PostMapping("/add")
    public String addComponent(@Valid @ModelAttribute("component") Component component, BindingResult result) {
        if (result.hasErrors()) {
            return "add-component";
        }
        componentService.saveComponent(component);
        return "redirect:/components";
    }

    @GetMapping("/edit/{id}")
    public String editComponentForm(@PathVariable("id") Integer id, Model model) {
        Component component = componentService.findComponentById(id).orElse(null);
        if (component != null) {
            model.addAttribute("component", component);
            return "edit-component";
        }
        return "redirect:/components";
    }

    @PostMapping("/update/{id}")
    public String updateComponent(@PathVariable("id") Integer id, @Valid @ModelAttribute("component") Component component, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-component";
        }
        component.setComponentId(id);
        componentService.updateComponent(component);
        return "redirect:/components";
    }

    @GetMapping("/delete/{id}")
    public String deleteComponent(@PathVariable("id") Integer id) {
        componentService.deleteComponentById(id);
        return "redirect:/components";
    }
}
