package pro.programista.infrastructure.context.persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import java.util.Map;
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
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pro.programista.domain.event.DomainEventBus;
import pro.programista.domain.repository.TradePairRepository;
import pro.programista.domain.repository.WalletRepository;
import pro.programista.domain.service.TradePairService;
import pro.programista.domain.service.WalletService;
import pro.programista.domain.service.impl.TradePairServiceImpl;
import pro.programista.domain.service.impl.WalletServiceImpl;
import pro.programista.infrastructure.constants.WalletBeanConstants;
import pro.programista.infrastructure.persistence.wallet.TradePairEntityRepository;
import pro.programista.infrastructure.persistence.wallet.TradePairJpaRepository;
import pro.programista.infrastructure.persistence.wallet.WalletEntityRepository;
import pro.programista.infrastructure.persistence.wallet.WalletJpaRepository;

@Data
@EnableJpaRepositories(
    entityManagerFactoryRef = WalletBeanConstants.WALLET_ENTITY_MANAGER,
    transactionManagerRef = WalletBeanConstants.TRANSACTION_MANAGER,
    basePackageClasses = WalletJpaRepository.class
)
@EnableTransactionManagement
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
    migrate(dataSource);

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
  public TradePairRepository tradePairRepository(TradePairJpaRepository tradePairJpaRepository) {
    return new TradePairEntityRepository(tradePairJpaRepository);
  }

  @Bean
  public WalletService walletService(WalletRepository walletRepository, DomainEventBus domainEventBus) {
    return new WalletServiceImpl(walletRepository, domainEventBus);
  }

  @Bean
  public TradePairService tradePairService(TradePairRepository tradePairRepository) {
    return new TradePairServiceImpl(tradePairRepository);
  }

  public void migrate(DataSource dataSource) {
    Flyway.configure()
        .schemas("wallet")
        .locations("classpath:db/migration/wallet")
        .dataSource(dataSource)
        .load()
        .migrate();
  }

}
