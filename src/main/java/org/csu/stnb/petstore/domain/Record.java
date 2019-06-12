package org.csu.stnb.petstore.domain;

import java.io.Serializable;

public class Record implements Serializable {
  private String item;
  private String date;

  public String getDate() {
    return date;
  }

  public String getItem() {
    return item;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public void setItem(String item) {
    this.item = item;
  }
}
