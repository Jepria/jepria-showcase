package com.technology.jep.jepriashowcase.goods;

import org.jepria.server.data.OptionDto;

import java.util.List;

public interface GoodsService {
  public List<OptionDto<String>> getUnit();

  public List<OptionDto<String>> getGoodsType();

  public List<OptionDto<String>> getMotivationType();

  public List<OptionDto<String>> getGoodsSegment();
}
