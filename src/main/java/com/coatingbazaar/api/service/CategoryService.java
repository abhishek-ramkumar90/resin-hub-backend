package com.coatingbazaar.api.service;

import com.coatingbazaar.api.model.Category;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryService {

    public List<Category> getAllCategories() {
        return Arrays.asList(
            new Category("epoxy-resins", "Epoxy Resins", "High-performance epoxy powder coating resins", 45,
                Arrays.asList("Bisphenol A Epoxy", "Novolac Epoxy", "Hybrid Epoxy", "Low-Cure Epoxy", "Anti-Corrosion Epoxy")),
            new Category("polyester-resins", "Polyester Resins", "Weather-resistant polyester resins for exterior use", 38,
                Arrays.asList("TGIC Polyester", "Primid Polyester", "Super Durable Polyester", "HAA Polyester", "Hybrid Polyester")),
            new Category("pigments-dyes", "Pigments & Dyes", "Color pigments and dyes for powder coatings", 120,
                Arrays.asList("Titanium Dioxide", "Iron Oxide", "Carbon Black", "Organic Pigments", "Metallic Pigments")),
            new Category("hardeners", "Hardeners", "Curing agents and crosslinkers", 28,
                Arrays.asList("TGIC Hardener", "Primid Hardener", "DICY Hardener", "Phenolic Hardener", "Anhydride Hardener")),
            new Category("additives", "Additives", "Flow agents, degassing agents, and modifiers", 65,
                Arrays.asList("Flow Agents", "Degassing Agents", "Texturing Agents", "Anti-Cratering", "Wax Additives")),
            new Category("fillers", "Fillers", "Calcium carbonate, barium sulfate, and more", 32,
                Arrays.asList("Calcium Carbonate", "Barium Sulfate", "Silica", "Talc", "Mica")),
            new Category("matting-agents", "Matting Agents", "Achieve desired gloss levels in coatings", 18,
                Arrays.asList("Silica-Based", "Wax-Based", "Polymer-Based", "PTFE Matting", "Hybrid Matting")),
            new Category("processing-equipment", "Processing Equipment", "Extruders, mills, and application equipment", 22,
                Arrays.asList("Extruders", "Grinding Mills", "Spray Guns", "Curing Ovens", "Sieving Equipment"))
        );
    }

    public Category getCategoryById(String id) {
        return getAllCategories().stream()
            .filter(c -> c.getId().equals(id))
            .findFirst()
            .orElse(null);
    }
}
