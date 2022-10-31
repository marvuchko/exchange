package pro.programista.infrastructure.context.persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pro.programista.domain.entity.User;
import pro.programista.domain.event.DomainEventBus;
import pro.programista.domain.repository.UserRepository;
import pro.programista.domain.service.UserService;
import pro.programista.domain.service.impl.UserServiceImpl;
import pro.programista.domain.service.Cache;
import pro.programista.infrastructure.constants.UserBeanConstants;
import pro.programista.infrastructure.persistence.user.UserCacheableJpaRepository;
import pro.programista.infrastructure.persistence.user.UserEntityRepository;
import pro.programista.infrastructure.persistence.user.UserJpaRepository;

@Data
@EnableJpaRepositories(
    entityManagerFactoryRef = UserBeanConstants.USER_ENTITY_MANAGER,
    transactionManagerRef = UserBeanConstants.TRANSACTION_MANAGER,
    basePackageClasses = UserJpaRepository.class
)
@EnableTransactionManagement
@EqualsAndHashCode(callSuper = true)
@Configuration(UserBeanConstants.USER_PERSISTENCE)
@ConfigurationProperties(prefix = "user.datasource")
public class UserPersistenceContext extends HikariConfig {

  @Primary
  @Bean(UserBeanConstants.USER_DATA_SOURCE)
  public DataSource dataSource() {
    return new HikariDataSource(this);
  }

  @Primary
  @Bean(value = UserBeanConstants.USER_ENTITY_MANAGER)
  public LocalContainerEntityManagerFactoryBean entityManager(
      @Qualifier(UserBeanConstants.USER_DATA_SOURCE) DataSource dataSource,
      EntityManagerFactoryBuilder factoryBuilder,
      Environment environment
  ) {
    migrate(dataSource);

    Map<String, String> properties = new HashMap<>();
    properties.put("hibernate.dialect", environment.getProperty("user.datasource.dialect"));

    return factoryBuilder
        .dataSource(dataSource)
        .packages(UserJpaRepository.class.getPackageName())
        .persistenceUnit(UserBeanConstants.USER_DATA_SOURCE)
        .properties(properties)
        .build();
  }

  @Primary
  @Bean(UserBeanConstants.TRANSACTION_MANAGER)
  public PlatformTransactionManager transactionManager(
      @Qualifier(UserBeanConstants.USER_ENTITY_MANAGER) EntityManagerFactory entityManagerFactory
  ) {
    return new JpaTransactionManager(entityManagerFactory);
  }

  @Bean(value = UserBeanConstants.USER_REPOSITORY)
  public UserRepository userRepository(
      @Qualifier(UserBeanConstants.CACHE) Cache<UUID, User> cache,
      UserJpaRepository userJpaRepository
  ) {
    var repository = new UserEntityRepository(userJpaRepository);
    return new UserCacheableJpaRepository(cache, repository);
  }

  @Bean(UserBeanConstants.USER_SERVICE)
  public UserService userServiceService(UserRepository userRepository, DomainEventBus eventBus) {
    return new UserServiceImpl(userRepository, eventBus);
  }

  public void migrate(DataSource dataSource) {
    Flyway.configure()
        .schemas("user")
        .locations("classpath:db/migration/user")
        .dataSource(dataSource)
        .load()
        .migrate();
  }

}
