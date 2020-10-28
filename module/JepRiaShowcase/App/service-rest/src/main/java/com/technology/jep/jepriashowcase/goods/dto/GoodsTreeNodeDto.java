package com.technology.jep.jepriashowcase.goods.dto;

public class GoodsTreeNodeDto {
  private Integer value;
  private String name;
  private Boolean hasChilden;
  private Boolean hasGoodsLink;
  private Boolean hasDescendantGoodsLink;

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getHasChilden() {
    return hasChilden;
  }

  public void setHasChilden(Boolean hasChilden) {
    this.hasChilden = hasChilden;
  }

  public Boolean getHasGoodsLink() {
    return hasGoodsLink;
  }

  public void setHasGoodsLink(Boolean hasGoodsLink) {
    this.hasGoodsLink = hasGoodsLink;
  }

  public Boolean getHasDescendantGoodsLink() {
    return hasDescendantGoodsLink;
  }

  public void setHasDescendantGoodsLink(Boolean hasDescendantGoodsLink) {
    this.hasDescendantGoodsLink = hasDescendantGoodsLink;
  }
}
