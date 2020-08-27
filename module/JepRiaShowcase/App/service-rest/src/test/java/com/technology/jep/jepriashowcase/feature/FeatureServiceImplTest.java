package com.technology.jep.jepriashowcase.feature;

import static org.mockito.Mockito.when;

import com.technology.jep.jepriashowcase.feature.dao.FeatureDao;
import java.util.ArrayList;
import java.util.List;
import org.jepria.server.data.OptionDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FeatureServiceImplTest {

  @Mock
  FeatureDao dao;

  @Test
  void getFeatureOperator() {
    List<OptionDto<Integer>> expectedOptions = new ArrayList<>();
    OptionDto<Integer> optionDto = new OptionDto<>();
    optionDto.setValue(12);
    optionDto.setName("Name");
    expectedOptions.add(optionDto);

    when(this.dao.getFeatureOperator()).thenReturn(expectedOptions);

    FeatureServiceImpl service = new FeatureServiceImpl(dao);

    List<OptionDto<Integer>> operatorOptions = service.getFeatureOperator();

    Assertions.assertEquals(expectedOptions, operatorOptions, "getFeatureOperator error");
  }

  @Test
  void getFeatureStatus() {
    List<OptionDto<String>> expectedOptions = new ArrayList<>();
    OptionDto<String> optionDto = new OptionDto<>();
    optionDto.setValue("Value");
    optionDto.setName("Name");
    expectedOptions.add(optionDto);

    when(this.dao.getFeatureStatus()).thenReturn(expectedOptions);

    FeatureServiceImpl service = new FeatureServiceImpl(dao);

    List<OptionDto<String>> statusOptions = service.getFeatureStatus();

    Assertions.assertEquals(expectedOptions, statusOptions, "getFeatureStatus error");
  }
}