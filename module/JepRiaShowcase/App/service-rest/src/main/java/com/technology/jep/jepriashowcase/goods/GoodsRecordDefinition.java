package com.technology.jep.jepriashowcase.goods;

import com.technology.jep.jepriashowcase.goods.dto.GoodsCreateDto;
import com.technology.jep.jepriashowcase.goods.dto.GoodsDto;
import com.technology.jep.jepriashowcase.goods.dto.GoodsSearchDto;
import com.technology.jep.jepriashowcase.goods.dto.GoodsUpdateDto;
import org.jepria.server.data.RecordDefinitionDtoImpl;

// TODO скорее всего, имеет смысл по-прежнему поддерживать синглтон этого класса, чтобы не выполнять тяжеловесную инициализацию почём зря
public class GoodsRecordDefinition extends RecordDefinitionDtoImpl {

  public GoodsRecordDefinition() {
    super(GoodsCreateDto.class,
        GoodsDto.class,
        GoodsSearchDto.class,
        GoodsUpdateDto.class);
  }

}
