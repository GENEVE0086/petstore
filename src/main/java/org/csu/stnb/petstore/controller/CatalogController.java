package org.csu.stnb.petstore.controller;

import org.csu.stnb.petstore.domain.Category;
import org.csu.stnb.petstore.domain.Product;
import org.csu.stnb.petstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CatalogController {

    @Autowired
    CatalogService catalogService;

    @GetMapping("/catalog/main")
    public String viewMain(){
        return "catalog/main";
    }

    @GetMapping("/catalog/category")
    public String viewCategory(@RequestParam("categoryId") String categoryId, Model model) {
        if (categoryId != null) {
            Category category = catalogService.getCategory(categoryId);
            List<Product> productList = catalogService.getProductListByCategory(categoryId);
            model.addAttribute("category", category);
            model.addAttribute("productList",productList);
        }
        return "catalog/category";
    }
}
