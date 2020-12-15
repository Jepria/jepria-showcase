package com.technology.jep.jepriashowcase.main.rest;

import org.jepria.server.service.rest.jersey.ApplicationConfigBase;
import org.jepria.server.service.security.PrincipalImpl;

import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

/**
 * Базовая конфигурация для запука теста.
 */
public class TestApplicationConfig extends ApplicationConfigBase {

  public TestApplicationConfig(final PrincipalImpl credential) {
    register((ContainerRequestFilter) crf -> crf.setSecurityContext(new SecurityContext() {
      @Override
      public Principal getUserPrincipal() {
        return credential;
      }

      @Override
      public boolean isUserInRole(String s) {
        return true;
      }

      @Override
      public boolean isSecure() {
        return false;
      }

      @Override
      public String getAuthenticationScheme() {
        return "BASIC";
      }
    }));
  }

  /**
   * TODO: Научиться мокать представление db в {@link org.jepria.server.service.security.HttpBasicDynamicFeature},
   * чтобы не указывать SecurityContext вручную.
   */
  @Override
  protected void registerHttpBasicDynamicFeature() {}

  @Override
  protected void registerRolesAllowedDynamicFeature() {}
}
