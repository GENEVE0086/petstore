package org.csu.stnb.petstore.persistence;

import org.csu.stnb.petstore.domain.Item;

import java.util.List;
import java.util.Map;

public interface ItemMapper {
    void updateInventoryQuantity(Map<String, Object> param);

    int getInventoryQuantity(String itemId);

    List<Item> getItemListByProduct(String productId);

    Item getItem(String itemId);
}

