package com.technology.jep.jepriashowcase.goods.dto;

import java.util.List;

public class GoodsSearchDto {
  private Integer goodsId;
  private Integer supplierId;
  private String goodsName;
  private String goodsTypeCode;
  private List<String> goodsSegmentCode;
  private List<Integer> goodsCatalogIdList;
  private Integer maxRowCount;

  public Integer getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(Integer goodsId) {
    this.goodsId = goodsId;
  }

  public Integer getSupplierId() {
    return supplierId;
  }

  public void setSupplierId(Integer supplierId) {
    this.supplierId = supplierId;
  }

  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }

  public String getGoodsTypeCode() {
    return goodsTypeCode;
  }

  public void setGoodsTypeCode(String goodsTypeCode) {
    this.goodsTypeCode = goodsTypeCode;
  }

  public List<String> getGoodsSegmentCode() {
    return goodsSegmentCode;
  }

  public void setGoodsSegmentCode(List<String> goodsSegmentCode) {
    this.goodsSegmentCode = goodsSegmentCode;
  }

  public List<Integer> getGoodsCatalogIdList() {
    return goodsCatalogIdList;
  }

  public void setGoodsCatalogIdList(List<Integer> goodsCatalogIdList) {
    this.goodsCatalogIdList = goodsCatalogIdList;
  }

  public Integer getMaxRowCount() {
    return maxRowCount;
  }

  public void setMaxRowCount(Integer maxRowCount) {
    this.maxRowCount = maxRowCount;
  }
}
