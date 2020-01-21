package com.technology.jep.jepriashowcase.custom.client.ui.plain;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.technology.jep.jepria.client.ui.eventbus.main.MainEventBus;
import com.technology.jep.jepria.client.ui.main.MainClientFactoryImpl;
import com.technology.jep.jepria.shared.service.JepMainServiceAsync;
import com.technology.jep.jepriashowcase.custom.client.CustomClientFactoryImpl;

public class CustomModulePresenterTest {

  @Test
  public void testOnSsoRequestFailure() {
    CustomClientFactoryImpl clientFactoryMock = mock(CustomClientFactoryImpl.class);
    @SuppressWarnings("unchecked")
    MainClientFactoryImpl<MainEventBus, JepMainServiceAsync> mainClientFactoryImplMock = mock(MainClientFactoryImpl.class);
    when(clientFactoryMock.getMainClientFactory()).thenReturn(mainClientFactoryImplMock);
    CustomModuleView moduleViewMock = mock(CustomModuleView.class);
    when(clientFactoryMock.getModuleView()).thenReturn(moduleViewMock);
    CustomModulePresenter presenter = new CustomModulePresenter("Custom", null, clientFactoryMock);
    presenter.bind();
    presenter.onSsoRequestFailure(new Exception("Error message"));
    verify(moduleViewMock).hideLoadingIndicator();
    verify(moduleViewMock).showOrClearError("Error message");
  }
}
