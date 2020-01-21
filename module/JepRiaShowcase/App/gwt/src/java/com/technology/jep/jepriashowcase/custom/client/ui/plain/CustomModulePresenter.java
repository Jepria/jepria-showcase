package com.technology.jep.jepriashowcase.custom.client.ui.plain;

import static com.technology.jep.jepria.shared.JepRiaConstant.JEP_USER_NAME_FIELD_NAME;
import static com.technology.jep.jepria.shared.JepRiaConstant.JEP_USER_ROLES_FIELD_NAME;
import static com.technology.jep.jepria.shared.field.JepFieldNames.OPERATOR_ID;
import static com.technology.jep.jepriashowcase.custom.client.CustomClientConstant.customText;
import static com.technology.jep.jepriashowcase.custom.shared.CustomConstant.AUTHENTICATION_SERVLET_URI;
import static com.technology.jep.jepriashowcase.custom.shared.CustomConstant.REDIRECT_TO_SESSION_REQUEST_PARAMETER;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.URL_FULL_SCREEN;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.URL_SEARCH_MODULE;
import static com.technology.jep.jepriashowcase.main.shared.JepRiaShowcaseConstant.TOMCAT_AUTLOGON_URL;
import static com.technology.jep.jepriashowcase.main.shared.JepRiaShowcaseConstant.URL_EMBEDDED;

import java.util.List;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.technology.jep.jepria.client.async.JepAsyncCallback;
import com.technology.jep.jepria.client.history.scope.JepScopeStack;
import com.technology.jep.jepria.client.message.ErrorDialog;
import com.technology.jep.jepria.client.security.ClientSecurity;
import com.technology.jep.jepria.client.ui.eventbus.plain.PlainEventBus;
import com.technology.jep.jepria.client.ui.plain.PlainModulePresenter;
import com.technology.jep.jepria.client.util.JepClientUtil;
import com.technology.jep.jepria.shared.dto.JepDto;
import com.technology.jep.jepria.shared.service.JepMainServiceAsync;
import com.technology.jep.jepria.shared.util.JepRiaUtil;
import com.technology.jep.jepriashowcase.custom.client.CustomClientFactoryImpl;
import com.technology.jep.jepriashowcase.custom.client.ui.ZIndexDialog;
import com.technology.jep.jepriashowcase.custom.shared.service.CustomServiceAsync;
import com.technology.jep.jepriashowcase.main.shared.JepRiaShowcaseConstant;

public class CustomModulePresenter
  extends PlainModulePresenter<CustomModuleView, PlainEventBus, CustomServiceAsync, CustomClientFactoryImpl> {
  
  private JepMainServiceAsync mainService = clientFactory.getMainClientFactory().getMainService();
  
  private ZIndexDialog zIndexDialog;
  
  private ZIndexDialog getZIndexDialog() {
    if (zIndexDialog == null) {
      zIndexDialog = new ZIndexDialog();
    }
    return zIndexDialog;
  }

  public CustomModulePresenter(String moduleId, Place place, CustomClientFactoryImpl clientFactory) {
    super(moduleId, place, clientFactory);
  }
  
  @Override
  protected void bind() {
    super.bind();        
    view.addFullScreenButtonClickHandler(event -> Window.Location.assign(URL_FULL_SCREEN));    
    view.addEmbeddedButtonClickHandler(event -> Window.Location.assign(URL_EMBEDDED));    
    view.addSearchButtonClickHandler(event -> Window.Location.assign(URL_SEARCH_MODULE));    
    view.addErrorButtonClickHandler(event -> new ErrorDialog(customText.custom_errorDialog_title(),
        new Throwable(customText.custom_errorDialog_throwableMessage()), 
        customText.custom_errorDialog_message()).show());
    // TODO Выше объект каждый раз заново, а здесь используется повторно. Есть ли разница?
    view.addZIndexButtonClickHandler(event -> getZIndexDialog().show());    
    view.addCurrentUserButtonClickHandler(this::onCurrentUserButtonClick);    
    view.addAuthorizationButtonClickHandler(this::onAuthorizationButtonClick);    
    view.addExitButtonClickHandler(this::onExitButtonClick);
    view.addGoToUrlButtonClickHandler(this::onGoToUrlButtonClick);    
    view.addTransactionButtonClickHandler(event -> 
      service.transaction(new JepAsyncCallback<Void>(){
        @Override
        public void onSuccess(Void result) {
          messageBox.alert(customText.custom_transactionSuccessfulAlert_message());
        }}));
    view.addAuthenticationServletButtonClickHandler(this::onAuthenticationServletButtonClick);
    
    view.setUrlTextFieldAllowBlank(false);
  }

  /**
   * Установка главного виджета(-контейнера) приложения.<br/>
   * В методе используется вызов вида : <code>mainEventBus.setMainView(clientFactory.getMainClientFactory().getMainView());</code> <br/>
   * При этом, при передаче <code>null</code> в качестве главного виджета приложения, текущий главный виджет удаляется с RootPanel'и.<br/>
   * Т.о., перегрузкой данного метода можно установить, при заходе на модуль приложения, любой главный виджет приложения или скрыть текущий.
   */
  protected void setMainView() {
    Log.trace(this.getClass() + ".setMainView()");
  
    mainEventBus.setMainView(null);
    
    String username = ClientSecurity.instance.getUsername();
    boolean isGuest = JepRiaUtil.isEmpty(username);
    if (!isGuest){
      view.setUsername(username);
      view.toggleAuthorizationPanel();
    }
  }

  void onExitButtonClick(ClickEvent event) {
    view.clearCredential();
    // Самостоятельный выход.
    mainService.logout(Window.Location.getHref(), new AsyncCallback<String>() {
      public void onFailure(Throwable caught) {
        view.toggleAuthorizationPanel();
      }
  
      public void onSuccess(String result) {
        view.toggleAuthorizationPanel();
      }
    });
  }

  void onCurrentUserButtonClick(ClickEvent event) {
    mainService.getUserData(new JepAsyncCallback<JepDto>() {
      @Override
      public void onSuccess(JepDto result) {
        Integer currentOperatorId = (Integer) result.get(OPERATOR_ID);
        service.getOperatorName(currentOperatorId, new JepAsyncCallback<String>() {
          public void onSuccess(String operatorName) {
            messageBox.alert(customText.custom_currentUser() + " : " + operatorName);
          }
        });
      }
    });
  }

  void onAuthenticationServletButtonClick(ClickEvent event) {
    service.getRedirectToSessionId(Window.Location.getHref(), new JepAsyncCallback<String>() {
      @Override
      public void onSuccess(String key) {
        String redirectUri = AUTHENTICATION_SERVLET_URI + "?" + REDIRECT_TO_SESSION_REQUEST_PARAMETER + "=" + key;
        messageBox.alert(JepClientUtil.substitute(customText.custom_authenticationDialog_message(), redirectUri));
      }
    });
  }

  void onAuthorizationButtonClick(ClickEvent event) {
    final Credential userCredential = view.getUserCredential();
    view.showOrClearError(null);
    if (userCredential == null){
      view.showOrClearError(customText.custom_authorizationError_loginOrPasswordEmpty());
    }
    else {
      view.showLoadingIndicator();
      // Осуществляем запрос авторизации к Sso.
      String ssoUrl = JepClientUtil.substitute(TOMCAT_AUTLOGON_URL, userCredential.getLogin(), userCredential.getPassword());
      sendRequest(ssoUrl, RequestBuilder.POST, new RequestCallback() {
        @Override
        public void onResponseReceived(Request request, Response response) {
          onSsoRequestSuccess(response);
        }
        @Override
        public void onError(Request request, Throwable exception) {
          onSsoRequestFailure(exception);
        }
      });
    }
  }
  
  private void sendRequest(String requestUrl, RequestBuilder.Method method, RequestCallback callback){
    RequestBuilder rBuilder = new RequestBuilder(method, URL.encode(requestUrl));
    try {
      rBuilder.sendRequest(null, callback);
    }
    catch(RequestException exception){
      onSsoRequestFailure(exception);
    }
  }

  void onSsoRequestSuccess(Response response) {
    switch(response.getStatusCode()){
      case Response.SC_OK : {
        boolean isSuccessfulAuthorized = JepRiaShowcaseConstant.SUCCESS.equalsIgnoreCase(response.getText().trim());
        if (isSuccessfulAuthorized){
          mainService.getUserData(new JepAsyncCallback<JepDto>(){
            @Override
            @SuppressWarnings("unchecked")
            public void onSuccess(JepDto userData) {
              String username = (String)userData.get(JEP_USER_NAME_FIELD_NAME);
              
              ClientSecurity.instance.setOperatorId((Integer)userData.get(OPERATOR_ID));
              List<String> roles = (List<String>) userData.get(JEP_USER_ROLES_FIELD_NAME);
              ClientSecurity.instance.setRoles(roles == null ? null : roles.toArray(new String[roles.size()]));
              ClientSecurity.instance.setUsername(username);
              
              JepScopeStack.instance.setUserEntered();
              
              view.setUsername(username);
              view.hideLoadingIndicator();
              view.toggleAuthorizationPanel();
            }
          });
        }
        else {
          view.hideLoadingIndicator();
          view.showOrClearError(customText.custom_authorizationError_wrongCredential());
        }
        break;
      }
      case Response.SC_UNAUTHORIZED : {
        view.hideLoadingIndicator();
        view.showOrClearError(customText.custom_authorizationError_wrongCredential());
        break;
      }
      default : {
        view.hideLoadingIndicator();
        view.showOrClearError(customText.custom_authorizationError_noAnswerFromAuthorization());
        break;
      }
    }
  }

  void onSsoRequestFailure(Throwable exception) {
    view.hideLoadingIndicator();
    view.showOrClearError(exception.getMessage());
  }
  
  void onGoToUrlButtonClick(ClickEvent event) {
    if (view.isUrlTextFieldValid()) {
      Window.Location.assign(view.getUrlTextFieldValue());
    }
  }

}
