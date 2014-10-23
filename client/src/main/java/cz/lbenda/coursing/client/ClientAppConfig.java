/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.lbenda.coursing.client;

import cz.lbenda.coursing.server.PrepareDB;
import cz.lbenda.coursing.server.user.UserServiceImpl;
import java.io.File;
import java.util.Collections;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import net.sf.ehcache.CacheManager;
import org.h2.jdbcx.JdbcDataSource;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.cache.EhCacheBasedUserCache;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/** Annotation configuration for whole client side application
 * @author benzin
 */
@Configuration
@ComponentScan("cz.lbenda.coursing.server")
@EnableJpaRepositories("cz.lbenda.coursing.server")
@EnableTransactionManagement
@EnableGlobalMethodSecurity(securedEnabled = true)
@ImportResource( { "classpath*:/cz/lbenda/coursing/client/ClientAppConfig.xml" } )
public class ClientAppConfig {

  private static final Logger LOG = LoggerFactory.getLogger(ClientAppConfig.class);

  public static final String DATASORUCE_NAME = "jdbc/coursing";

  static {
    SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_GLOBAL);
  }

  public @Bean EntityManagerFactory entityManagerFactory() {
    try {
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
      factory.setPersistenceUnitName("coursing");

      /*
      Map<String, String> prop = new HashMap<>();
      prop.put("eclipselink.deploy-on-startup", "true");
      prop.put("eclipselink.ddl-generation", "create-or-extend-tables");
      prop.put("eclipselink.ddl-generation.output-mode", "database");
      prop.put("eclipselink.create-ddl-jdbc-file-name", "create.sql");
      prop.put("eclipselink.weaving", "static");
      prop.put("eclipselink.weaving.lazy", "true");
      prop.put("eclipselink.weaving.internal", "true");
      prop.put("eclipselink.logging.level", "SEVERE");
      prop.put("eclipselink.query-results-cache.type", "WEAK");
      prop.put("eclipselink.jdbc.batch-writing", "JDBC");
      prop.put("eclipselink.jdbc.batch-writing.size", "1000");

      factory.setJpaPropertyMap(prop);
      */

      // factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
      factory.afterPropertiesSet();

      return factory.getObject();
    } catch (Exception e) {
      LOG.trace("Faild create entityManagerFactory", e);
      throw new RuntimeException("Faild create entityManagerFactory", e);
    }
  }

  public @Bean DataSource dataSource() {
    LOG.debug("Datasource nebyl nalezen pomoci springu.");
    // try {
      JdbcDataSource ds = new JdbcDataSource();
      // ds.setUrl("jdbc:h2:tcp://localhost/coursing");
      // ds.setUrl("jdbc:h2:mem:coursing");
      // ds.setUrl("jdbc:h2:/tmp/coursing");
      ds.setUrl("jdbc:h2:" + localDb() + ";AUTO_SERVER=TRUE");
      ds.setUser("sa");
      ds.setPassword("");
      return ds;
      // Context ctx = new InitialContext();
      // DataSource ds = (DataSource) ctx.lookup(DATASORUCE_NAME);
    // } catch (NamingException e) {
    //  throw new RuntimeException("No data source with name '" + DATASORUCE_NAME + "' is defined.", e);
    // }
  }

  public @Bean String localDb() {
    String userDir = System.getProperty("user.home");
    if (userDir == null) { userDir = System.getProperty("user.dir"); }
    return userDir + File.separator + "coursing";
  }

  public @Bean PlatformTransactionManager transactionManager() {
    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory());
    return txManager;
  }

  public @Bean CacheManager cacheManager() {
    return CacheManager.create();
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
    result.setUserDetailsService(UserService());
    result.setUserCache(userCache());
    result.setPasswordEncoder(passwordEncoder());
    return result;
  }

  public @Bean PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  public @Bean AuthenticationManager authenticationManager() {
    return new ProviderManager(Collections.singletonList(authenticationProvider()));
  }

    /*
  public @Bean UserService userService() {
    try {
      return new UserServiceImpl();
    } catch (Exception e) {
      LOG.error("Failed when UserService created", e);
      throw new RuntimeException("Problem when UserSrviceImpl is created", e);
    }
  }
  */
  public @Bean UserDetailsService UserService() { return new UserServiceImpl(); }

  public @Bean PrepareDB prepareDB() { return new PrepareDB(); }
}
