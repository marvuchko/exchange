package pro.programista.infrastructure.context.persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import pro.programista.domain.event.DomainEventBus;
import pro.programista.domain.repository.WalletRepository;
import pro.programista.domain.service.WalletService;
import pro.programista.domain.service.impl.WalletServiceImpl;
import pro.programista.infrastructure.constants.WalletBeanConstants;
import pro.programista.infrastructure.persistence.wallet.WalletEntityRepository;
import pro.programista.infrastructure.persistence.wallet.WalletJpaRepository;

@Data
@EnableJpaRepositories(
    entityManagerFactoryRef = WalletBeanConstants.WALLET_ENTITY_MANAGER,
    transactionManagerRef = WalletBeanConstants.TRANSACTION_MANAGER,
    basePackageClasses = WalletJpaRepository.class
)
@EqualsAndHashCode(callSuper = true)
@Configuration(WalletBeanConstants.WALLET_PERSISTENCE)
@ConfigurationProperties(prefix = "wallet.datasource")
public class WalletPersistenceContext extends HikariConfig {

  @Bean(WalletBeanConstants.WALLET_DATA_SOURCE)
  public DataSource dataSource() {
    return new HikariDataSource(this);
  }

  @Bean(WalletBeanConstants.WALLET_ENTITY_MANAGER)
  public LocalContainerEntityManagerFactoryBean entityManager(
      @Qualifier(WalletBeanConstants.WALLET_DATA_SOURCE) DataSource dataSource,
      EntityManagerFactoryBuilder factoryBuilder,
      Environment environment
  ) {

    Map<String, String> properties = new HashMap<>();
    properties.put("hibernate.dialect", environment.getProperty("user.datasource.dialect"));

    return factoryBuilder
        .dataSource(dataSource)
        .packages(WalletJpaRepository.class.getPackageName())
        .persistenceUnit(WalletBeanConstants.WALLET_DATA_SOURCE)
        .properties(properties)
        .build();
  }

  @Bean(WalletBeanConstants.TRANSACTION_MANAGER)
  public PlatformTransactionManager transactionManager(
      @Qualifier(WalletBeanConstants.WALLET_ENTITY_MANAGER) EntityManagerFactory entityManagerFactory
  ) {
    return new JpaTransactionManager(entityManagerFactory);
  }

  @Bean
  public WalletRepository walletRepository(WalletJpaRepository walletJpaRepository) {
    return new WalletEntityRepository(walletJpaRepository);
  }

  @Bean
  public WalletService walletService(WalletRepository walletRepository, DomainEventBus domainEventBus) {
    return new WalletServiceImpl(walletRepository, domainEventBus);
  }

}
