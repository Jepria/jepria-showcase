package com.technology.jep.jepriashowcase.goods;

import com.technology.jep.jepriashowcase.goods.dao.GoodsDao;
import org.jepria.server.data.OptionDto;

import java.util.List;

public class GoodsServiceImpl implements GoodsService {

  GoodsDao dao;

  public GoodsServiceImpl(GoodsDao dao) {
    this.dao = dao;
  }

  @Override
  public List<OptionDto<String>> getUnit() {
    return this.dao.getUnit();
  }

  @Override
  public List<OptionDto<String>> getGoodsType() {
    return this.dao.getGoodsType();
  }

  @Override
  public List<OptionDto<String>> getMotivationType() {
    return this.dao.getMotivationType();
  }

  @Override
  public List<OptionDto<String>> getGoodsSegment() {
    return this.dao.getGoodsSegment();
  }
}
