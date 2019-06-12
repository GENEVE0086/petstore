package org.csu.stnb.petstore.persistence;

import org.csu.stnb.petstore.domain.Product;

import java.util.List;

public interface ProductMapper {

    List<Product> getProductListByCategory(String categoryId);

    Product getProduct(String productId);

    List<Product> searchProductList(String keyword);
}
