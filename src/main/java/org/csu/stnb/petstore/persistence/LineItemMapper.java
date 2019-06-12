package org.csu.stnb.petstore.persistence;

import org.csu.stnb.petstore.domain.LineItem;

import java.util.List;

public interface LineItemMapper {
    List<LineItem> getLineItemsByOrderId(int var1);

    void insertLineItem(LineItem var1);
}
