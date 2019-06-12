package org.csu.stnb.petstore.persistence;

import java.util.List;

import org.csu.stnb.petstore.domain.Category;

public interface CategoryMapper {
  Category getCategory(String categoryId);

  List<Category> getCategoryList();
}
