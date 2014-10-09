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
package cz.lbenda.coursing.server;

import cz.lbenda.coursing.server.user.UserServiceImpl;
import cz.lbenda.coursing.user.UserService;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import net.sf.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.cache.EhCacheBasedUserCache;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
@Configuration
@ImportResource( { "classpath*:/cz/lbenda/coursing/serverConfig.xml" } )
@ComponentScan("cz.lbenda.coursing")
@EnableJpaRepositories("cz.lbenda.coursing")
@EnableTransactionManagement
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ServerAppConfig extends WebSecurityConfigurerAdapter { //extends SecurityConfigurationAdapter {

  private static final Logger LOGGER = LoggerFactory.getLogger(ServerAppConfig.class);

  public static final String DATASORUCE_NAME = "jdbc/coursing";

  public @Bean EntityManagerFactory entityManagerFactory() {
    EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
    vendorAdapter.setDatabasePlatform("org.eclipse.persistence.platform.database.H2Platform");
    vendorAdapter.setShowSql(true);
    vendorAdapter.setGenerateDdl(true);
    EclipseLinkJpaDialect dialect = new EclipseLinkJpaDialect();
    dialect.setLazyDatabaseTransaction(true);

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setJpaDialect(dialect);
    factory.setPackagesToScan("cz.lbenda.coursing");
    factory.setDataSource(dataSource());
    factory.setPersistenceXmlLocation("classpath*:META-INF/persistence.xml");

    Map<String, String> prop = new HashMap<String, String>();
    prop.put("eclipselink.deploy-on-startup", "true");
    prop.put("eclipselink.ddl-generation", "create-or-extend-tables");
    prop.put("eclipselink.ddl-generation.output-mode", "database");
    prop.put("eclipselink.weaving", "static");
    prop.put("eclipselink.weaving.lazy", "true");
    prop.put("eclipselink.weaving.internal", "true");
    prop.put("eclipselink.logging.level", "SEVERE");
    prop.put("eclipselink.query-results-cache.type", "WEAK");
    prop.put("eclipselink.jdbc.batch-writing", "JDBC");
    prop.put("eclipselink.jdbc.batch-writing.size", "1000");

    factory.setJpaPropertyMap(prop);

    // factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
    factory.afterPropertiesSet();

    return factory.getObject();
  }

  public @Bean DataSource dataSource() {
    LOGGER.debug("Datasource nebyl nalezen pomoci springu.");
    try {
      Context ctx = new InitialContext();
      DataSource ds = (DataSource) ctx.lookup(DATASORUCE_NAME);
      return ds;
    } catch (NamingException e) {
      throw new RuntimeException("No data source with name '" + DATASORUCE_NAME + "' is defined.", e);
    }
  }

  public @Bean PlatformTransactionManager transactionManager() {
    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory());
    return txManager;
  }

  public @Bean CacheManager cacheManager() {
    return new CacheManager();
  }

  public @Bean EhCacheFactoryBean userCacheBackend() {
    EhCacheFactoryBean result = new EhCacheFactoryBean();
    result.setCacheManager(cacheManager());
    result.setCacheName("userCache");
    return result;
  }

  public @Bean UserCache userCache() {
    final EhCacheBasedUserCache result = new EhCacheBasedUserCache();
    result.setCache(userCacheBackend().getObject());
    return result;
  }

  public @Bean AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider result = new DaoAuthenticationProvider();
    result.setUserDetailsService(userDetails());
    result.setUserCache(userCache());
    result.setPasswordEncoder(new BCryptPasswordEncoder());
    return result;
  }

  public @Bean AuthenticationManager authenticationManager() {
    return new ProviderManager(Collections.singletonList(authenticationProvider()));
  }

  public @Bean UserService userService() { return new UserServiceImpl(); }
  public @Bean UserDetailsService userDetails() { return new UserServiceImpl(); }
}
