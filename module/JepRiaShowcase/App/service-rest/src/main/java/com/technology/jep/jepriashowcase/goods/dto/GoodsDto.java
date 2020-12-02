package com.technology.jep.jepriashowcase.goods.dto;

import org.jepria.server.data.OptionDto;
import org.jepria.server.data.PrimaryKey;

import java.math.BigDecimal;
import java.util.List;

public class GoodsDto {

  @PrimaryKey
  private Integer goodsId;
  private Integer supplierId;
  private String goodsName;
  OptionDto<String> goodsTypeCode;
  OptionDto<String> unitCode;
  OptionDto<String> motivationTypeCode;
  BigDecimal purchasingPrice;
  List<GoodsTreeNodeDto> goodsCatalogIdListExpanded;
  List<GoodsTreeNodeDto> goodsCatalogIdListChecked;
  File goodsPhoto;
  File goodsPortfolio;

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

  public OptionDto<String> getGoodsTypeCode() {
    return goodsTypeCode;
  }

  public void setGoodsTypeCode(OptionDto<String> goodsTypeCode) {
    this.goodsTypeCode = goodsTypeCode;
  }

  public OptionDto<String> getUnitCode() {
    return unitCode;
  }

  public void setUnitCode(OptionDto<String> unitCode) {
    this.unitCode = unitCode;
  }

  public OptionDto<String> getMotivationTypeCode() {
    return motivationTypeCode;
  }

  public void setMotivationTypeCode(OptionDto<String> motivationTypeCode) {
    this.motivationTypeCode = motivationTypeCode;
  }

  public BigDecimal getPurchasingPrice() {
    return purchasingPrice;
  }

  public void setPurchasingPrice(BigDecimal purchasingPrice) {
    this.purchasingPrice = purchasingPrice;
  }

  public List<GoodsTreeNodeDto> getGoodsCatalogIdListExpanded() {
    return goodsCatalogIdListExpanded;
  }

  public void setGoodsCatalogIdListExpanded(List<GoodsTreeNodeDto> goodsCatalogIdListExpanded) {
    this.goodsCatalogIdListExpanded = goodsCatalogIdListExpanded;
  }

  public List<GoodsTreeNodeDto> getGoodsCatalogIdListChecked() {
    return goodsCatalogIdListChecked;
  }

  public void setGoodsCatalogIdListChecked(List<GoodsTreeNodeDto> goodsCatalogIdListChecked) {
    this.goodsCatalogIdListChecked = goodsCatalogIdListChecked;
  }

  public File getGoodsPhoto() {
    return goodsPhoto;
  }

  public void setGoodsPhoto(File goodsPhoto) {
    this.goodsPhoto = goodsPhoto;
  }

  public File getGoodsPortfolio() {
    return goodsPortfolio;
  }

  public void setGoodsPortfolio(File goodsPortfolio) {
    this.goodsPortfolio = goodsPortfolio;
  }

  public static class File {
    String extension;
    String mimeType;

    public String getExtension() {
      return extension;
    }

    public void setExtension(String extension) {
      this.extension = extension;
    }

    public String getMimeType() {
      return mimeType;
    }

    public void setMimeType(String mimeType) {
      this.mimeType = mimeType;
    }
  }
}
