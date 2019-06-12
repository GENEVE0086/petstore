package org.csu.stnb.petstore.persistence;

import java.util.List;

import org.csu.stnb.petstore.domain.LineItem;


public interface LineItemMapper {
  List<LineItem> getLineItemsByOrderId(int var1);

  void insertLineItem(LineItem var1);
}
