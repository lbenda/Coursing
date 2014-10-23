/*
 * Copyright 2014 Lukas Benda <lbenda at lbenda.cz>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cz.lbenda.coursing.client;

import cz.lbenda.coursing.server.PrepareDB;
import cz.lbenda.coursing.service.SecurityService;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.core.AuthenticationException;

/**
 *  @author Lukas Benda <lbenda at lbenda.cz>
 */
public class ClientServiceLocator {

  private static final Logger LOG = LoggerFactory.getLogger(ClientServiceLocator.class);

  private final AnnotationConfigApplicationContext ctx;
  private static ClientServiceLocator instance;

  public static ClientServiceLocator getInstance() {
    try {
      if (instance == null) { instance = new ClientServiceLocator(); }
    } catch (Exception e) {
      LOG.error("Error when instance of ClientServiceLocator is created", e);
    }
    return instance;
  }

  //tunnel between our ServiceLocator and Spring - obtain bean from Spring by name
  public Object getBean(String beanName) {
    return this.ctx.getBean(beanName);
  }

  public <T> T getBean(Class<T> serviceInterface) {
    return (T) this.ctx.getBean(serviceInterface.getSimpleName());
  }

  //basic initialization of Spring context
  private ClientServiceLocator() {
    // Check if application have builded server part of application. This application is interpret as standalone.
    Class<?> appConfig = ClientAppConfig.class;
    /* Check if application is in single mode, or server-client mode
    try {
      appConfig = Class.forName("cz.lbenda.coursing.server.ServerAppConfig");
      LOG.info("The libraries contains ServerAppConfig, so the application is in stand alone mode.");

    } catch (ClassNotFoundException e) {
      LOG.info("The libraries didn't contains ServerAppConfig, so the application is in Server-Client mode.");
      appConfig = ClientAppConfig.class;
    }
    */
    this.ctx = new AnnotationConfigApplicationContext(appConfig);
    // ctx.refresh();
    LOG.trace("Defined beans: " + Arrays.toString(ctx.getBeanDefinitionNames()));

    SecurityService ss = (SecurityService) ctx.getBean(SecurityService.SERVICE_NAME);
    try {
      ss.login("sys", "sys");
      PrepareDB prepareDB = ctx.getBean(PrepareDB.class);
      prepareDB.prepareDB();
    } catch (AuthenticationException ex) {
      LOG.error("The database is already prepared. If you want new, pleas delete old database: " + ctx.getBean("localDb") + ".mv.db");
    }
    //    ctx.refresh();
  }
}
