package org.csu.stnb.petstore.controller;

import java.util.List;

import org.csu.stnb.petstore.domain.Category;
import org.csu.stnb.petstore.domain.Item;
import org.csu.stnb.petstore.domain.Product;
import org.csu.stnb.petstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("account")
public class CatalogController {

  @Autowired
  CatalogService catalogService;

  @GetMapping("/catalog/main")
  public String viewMain() {
    return "catalog/main";
  }

  /**
   * get访问/catalog/category.
   * @param categoryId 目标类型的id
   * @param model 向前段传递数据使用的model
   * @return 模板路径
   */
  @GetMapping("/catalog/category")
  public String viewCategory(@RequestParam("categoryId") String categoryId, Model model) {
    if (categoryId != null) {
      Category category = catalogService.getCategory(categoryId);
      List<Product> productList = catalogService.getProductListByCategory(categoryId);
      model.addAttribute("category", category);
      model.addAttribute("productList", productList);
    }
    return "catalog/category";
  }

  @GetMapping("/catalog/product")
  public String viewProduct(@RequestParam("productId") String productId, Model model) {
    if (productId != null) {
      Product product = catalogService.getProduct(productId);
      List<Item> itemList = catalogService.getItemListByProduct(productId);
      model.addAttribute("product", product);
      model.addAttribute("itemList", itemList);
    }
    return "catalog/product";
  }

  @GetMapping("/catalog/item")
  public String viewItem(@RequestParam("itemId") String itemId, Model model) {
    if (itemId != null) {
      Item item = catalogService.getItem(itemId);
      Product product = item.getProduct();
      model.addAttribute("product", product);
      model.addAttribute("item", item);
    }
    return "catalog/item";
  }

  @PostMapping("/catalog/searchProducts")
  public String viewSearch(@RequestParam("keyword") String keyword, Model model) {
    if (keyword != null) {
      List<Product> searchProductList = catalogService.searchProductList(keyword);
      model.addAttribute("searchProductList", searchProductList);
    }
    return "catalog/searchProducts";
  }
}
