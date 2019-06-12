package org.csu.stnb.petstore.service;

import java.util.List;

import org.csu.stnb.petstore.domain.Category;
import org.csu.stnb.petstore.domain.Item;
import org.csu.stnb.petstore.domain.Product;

public interface CatalogService {

  List<Category> getCategoryList();

  Category getCategory(String categoryId);

  Product getProduct(String productId);

  List<Product> getProductListByCategory(String categoryId);

  List<Product> searchProductList(String keyword);

  List<Item> getItemListByProduct(String productId);

  Item getItem(String itemId);

  boolean isItemInStock(String itemId);
}
