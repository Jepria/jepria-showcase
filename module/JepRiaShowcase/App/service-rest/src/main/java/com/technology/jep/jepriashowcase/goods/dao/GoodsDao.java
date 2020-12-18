package com.technology.jep.jepriashowcase.goods.dao;

import com.technology.jep.jepriashowcase.goods.dto.GoodsTreeNodeDto;
import org.jepria.server.data.Dao;
import org.jepria.server.data.OptionDto;

import java.util.List;

public interface GoodsDao extends Dao {
  List<OptionDto<String>>  getGoodsType();
  List<OptionDto<String>>  getUnit();
  List<OptionDto<String>>  getMotivationType();
  List<GoodsTreeNodeDto> getGoodsCatalog(Integer parentGoodsCatalogId, Integer goodsId);
  List<OptionDto<String>>  getGoodsSegment();
}
