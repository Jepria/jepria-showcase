package com.technology.jep.jepriashowcase.main.rest;

import org.glassfish.jersey.test.JerseyTest;
import org.jepria.server.service.security.PrincipalImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


/**
 * Кандидат в общие классы, пока размещен здесь.
 */
public class JepriaTest extends JerseyTest {

  protected static final PrincipalImpl credential = new PrincipalImpl("user", 123);

  /**
   * Совместимость с junit5
   * @throws Exception
   */
  @BeforeEach
  void before() throws Exception {
    super.setUp();
  }

  /**
   * Совместимость с junit5
   * @throws Exception
   */
  @AfterEach
  void after() throws Exception {
    super.tearDown();
  }
}
