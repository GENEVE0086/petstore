package org.csu.stnb.petstore.service;

import org.csu.stnb.petstore.domain.Category;
import org.csu.stnb.petstore.domain.Item;
import org.csu.stnb.petstore.domain.Product;

import java.util.List;

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
