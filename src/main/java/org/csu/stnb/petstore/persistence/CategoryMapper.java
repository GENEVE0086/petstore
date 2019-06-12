package org.csu.stnb.petstore.persistence;

import org.csu.stnb.petstore.domain.Category;

import java.util.List;

public interface CategoryMapper {
    Category getCategory(String categoryId);

    List<Category>  getCategoryList();
}
