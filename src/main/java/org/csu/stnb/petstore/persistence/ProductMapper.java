package org.csu.stnb.petstore.persistence;

import java.util.List;

import org.csu.stnb.petstore.domain.Product;

public interface ProductMapper {

  List<Product> getProductListByCategory(String categoryId);

  Product getProduct(String productId);

  List<Product> searchProductList(String keyword);
}
