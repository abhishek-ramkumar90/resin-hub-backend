package com.coatingbazaar.api.service;

import com.coatingbazaar.api.model.Product;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.*;

@Service
public class ProductService {

    private final Map<String, List<Product>> productsByCategory = new LinkedHashMap<>();

    @PostConstruct
    public void init() {
        productsByCategory.put("epoxy-resins", Arrays.asList(
            new Product("ep1", "Bisphenol A Epoxy Resin", "E-12", "Huntsman", "Mumbai", 285, "KG", -2.1, "23 min ago", "epoxy-resins"),
            new Product("ep2", "Bisphenol A Epoxy Resin", "E-12", "Huntsman", "Delhi", 290, "KG", -1.8, "23 min ago", "epoxy-resins"),
            new Product("ep3", "Novolac Epoxy Resin", "N-740", "Atul Ltd", "Ahmedabad", 320, "KG", 1.5, "45 min ago", "epoxy-resins"),
            new Product("ep4", "Hybrid Epoxy Resin", "HY-50", "Kukdo Chemical", "Chennai", 265, "KG", -0.8, "1 hr ago", "epoxy-resins"),
            new Product("ep5", "Low-Cure Epoxy Resin", "LC-200", "Hexion", "Bangalore", 310, "KG", 2.3, "2 hrs ago", "epoxy-resins"),
            new Product("ep6", "Anti-Corrosion Epoxy", "AC-100", "BASF", "Pune", 340, "KG", 0.5, "3 hrs ago", "epoxy-resins")
        ));
        productsByCategory.put("polyester-resins", Arrays.asList(
            new Product("po1", "TGIC Polyester Resin", "T-90", "Allnex", "Mumbai", 230, "KG", 1.2, "15 min ago", "polyester-resins"),
            new Product("po2", "TGIC Polyester Resin", "T-90", "Allnex", "Kolkata", 235, "KG", 1.5, "15 min ago", "polyester-resins"),
            new Product("po3", "Primid Polyester Resin", "P-60", "DSM", "Delhi", 245, "KG", -0.5, "30 min ago", "polyester-resins"),
            new Product("po4", "Super Durable Polyester", "SD-800", "Eternis", "Chennai", 275, "KG", 3.1, "1 hr ago", "polyester-resins"),
            new Product("po5", "HAA Polyester Resin", "H-45", "Cytec", "Ahmedabad", 255, "KG", -1.2, "2 hrs ago", "polyester-resins")
        ));
        productsByCategory.put("pigments-dyes", Arrays.asList(
            new Product("pg1", "Titanium Dioxide", "R-902+", "Chemours", "Mumbai", 310, "KG", -3.2, "10 min ago", "pigments-dyes"),
            new Product("pg2", "Titanium Dioxide", "R-706", "Chemours", "Delhi", 305, "KG", -2.8, "10 min ago", "pigments-dyes"),
            new Product("pg3", "Iron Oxide Red", "130M", "Lanxess", "Chennai", 85, "KG", 0.8, "25 min ago", "pigments-dyes"),
            new Product("pg4", "Carbon Black", "N330", "Birla Carbon", "Kolkata", 120, "KG", 1.5, "40 min ago", "pigments-dyes"),
            new Product("pg5", "Organic Yellow Pigment", "PY-83", "Heubach", "Ahmedabad", 450, "KG", -1.0, "1 hr ago", "pigments-dyes"),
            new Product("pg6", "Metallic Silver Pigment", "MS-200", "Eckart", "Bangalore", 890, "KG", 0.3, "2 hrs ago", "pigments-dyes")
        ));
        productsByCategory.put("hardeners", Arrays.asList(
            new Product("hd1", "TGIC Hardener", "PT-810", "Huntsman", "Mumbai", 520, "KG", 2.5, "20 min ago", "hardeners"),
            new Product("hd2", "Primid XL-552", "XL-552", "EMS-Chemie", "Delhi", 680, "KG", -1.0, "35 min ago", "hardeners"),
            new Product("hd3", "DICY Hardener", "D-100", "Evonik", "Chennai", 380, "KG", 0.7, "1 hr ago", "hardeners"),
            new Product("hd4", "Phenolic Hardener", "PH-300", "Hexion", "Pune", 420, "KG", -2.1, "2 hrs ago", "hardeners")
        ));
        productsByCategory.put("additives", Arrays.asList(
            new Product("ad1", "Flow Agent", "Resiflow PV-88", "Estron", "Mumbai", 950, "KG", 0.5, "18 min ago", "additives"),
            new Product("ad2", "Degassing Agent", "Benzoin", "Estron", "Delhi", 780, "KG", -0.3, "30 min ago", "additives"),
            new Product("ad3", "Texturing Agent", "TX-500", "Troy", "Ahmedabad", 1100, "KG", 1.8, "45 min ago", "additives"),
            new Product("ad4", "Anti-Cratering Agent", "AC-300", "BYK", "Chennai", 1250, "KG", 0.2, "1 hr ago", "additives"),
            new Product("ad5", "Wax Additive", "Ceraflour 950", "BYK", "Bangalore", 860, "KG", -1.5, "2 hrs ago", "additives")
        ));
        productsByCategory.put("fillers", Arrays.asList(
            new Product("fl1", "Calcium Carbonate", "10 Micron", "Omya", "Rajasthan", 12, "KG", 0.0, "30 min ago", "fillers"),
            new Product("fl2", "Barium Sulfate", "B-100", "Solvay", "Mumbai", 45, "KG", -0.5, "1 hr ago", "fillers"),
            new Product("fl3", "Precipitated Silica", "S-200", "Evonik", "Delhi", 120, "KG", 1.0, "2 hrs ago", "fillers"),
            new Product("fl4", "Talc Powder", "T-Micro", "Imerys", "Udaipur", 18, "KG", 0.2, "3 hrs ago", "fillers")
        ));
        productsByCategory.put("matting-agents", Arrays.asList(
            new Product("ma1", "Silica Matting Agent", "OK-412", "Evonik", "Mumbai", 680, "KG", -1.2, "25 min ago", "matting-agents"),
            new Product("ma2", "Wax-Based Matting", "Ceraflour 988", "BYK", "Delhi", 920, "KG", 0.8, "45 min ago", "matting-agents"),
            new Product("ma3", "PTFE Matting Agent", "MP-1000", "Shamrock", "Chennai", 1450, "KG", 2.0, "1 hr ago", "matting-agents")
        ));
        productsByCategory.put("processing-equipment", Arrays.asList(
            new Product("eq1", "Twin Screw Extruder", "TSE-30", "Coperion", "Mumbai", 850000, "Unit", 0.0, "1 day ago", "processing-equipment"),
            new Product("eq2", "ACM Grinding Mill", "ACM-20", "Hosokawa", "Delhi", 620000, "Unit", 0.0, "1 day ago", "processing-equipment"),
            new Product("eq3", "Electrostatic Spray Gun", "OptiFlex F", "Gema", "Bangalore", 45000, "Unit", -2.0, "3 hrs ago", "processing-equipment"),
            new Product("eq4", "Powder Curing Oven", "PCO-500", "Thermax", "Pune", 380000, "Unit", 1.5, "1 day ago", "processing-equipment")
        ));
    }

    public Map<String, List<Product>> getAllProducts() {
        return productsByCategory;
    }

    public List<Product> getProductsByCategory(String categoryId) {
        return productsByCategory.getOrDefault(categoryId, Collections.emptyList());
    }

    public List<Product> getAllProductsFlat() {
        List<Product> all = new ArrayList<>();
        productsByCategory.values().forEach(all::addAll);
        return all;
    }
}
