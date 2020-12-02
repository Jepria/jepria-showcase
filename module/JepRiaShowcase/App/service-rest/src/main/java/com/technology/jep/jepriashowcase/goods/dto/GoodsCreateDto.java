package com.technology.jep.jepriashowcase.goods.dto;

import java.math.BigDecimal;

public class GoodsCreateDto {
  private Integer supplierId;
  private String goodsName;
  private String goodsTypeCode;
  private String unitCode;
  private BigDecimal purchasingPrice;
  private String motivationTypeCode;
  private String goodsPhotoMimeType;
  private String goodsPhotoExtension;
  private String goodsPortfolioMimeType;
  private String goodsPortfolioExtension;

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

  public String getUnitCode() {
    return unitCode;
  }

  public void setUnitCode(String unitCode) {
    this.unitCode = unitCode;
  }

  public BigDecimal getPurchasingPrice() {
    return purchasingPrice;
  }

  public void setPurchasingPrice(BigDecimal purchasingPrice) {
    this.purchasingPrice = purchasingPrice;
  }

  public String getMotivationTypeCode() {
    return motivationTypeCode;
  }

  public void setMotivationTypeCode(String motivationTypeCode) {
    this.motivationTypeCode = motivationTypeCode;
  }

  public String getGoodsPhotoMimeType() {
    return goodsPhotoMimeType;
  }

  public void setGoodsPhotoMimeType(String goodsPhotoMimeType) {
    this.goodsPhotoMimeType = goodsPhotoMimeType;
  }

  public String getGoodsPhotoExtension() {
    return goodsPhotoExtension;
  }

  public void setGoodsPhotoExtension(String goodsPhotoExtension) {
    this.goodsPhotoExtension = goodsPhotoExtension;
  }

  public String getGoodsPortfolioMimeType() {
    return goodsPortfolioMimeType;
  }

  public void setGoodsPortfolioMimeType(String goodsPortfolioMimeType) {
    this.goodsPortfolioMimeType = goodsPortfolioMimeType;
  }

  public String getGoodsPortfolioExtension() {
    return goodsPortfolioExtension;
  }

  public void setGoodsPortfolioExtension(String goodsPortfolioExtension) {
    this.goodsPortfolioExtension = goodsPortfolioExtension;
  }
}
