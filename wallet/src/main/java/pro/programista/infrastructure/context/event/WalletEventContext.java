package pro.programista.infrastructure.context.event;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.programista.domain.event.DomainEventBus;
import pro.programista.infrastructure.constants.WalletBeanConstants;
import pro.programista.infrastructure.event.ForwardOnlyDomainEventBus;
import pro.programista.infrastructure.event.MeteredDomainEventBus;
import pro.programista.infrastructure.event.SpringDomainEventBus;

@Configuration
public class WalletEventContext {

  @Bean(WalletBeanConstants.DOMAIN_EVENT_BUS)
  @ConditionalOnMissingBean(DomainEventBus.class)
  public DomainEventBus domainEventBus(
      ApplicationEventPublisher publisher,
      MeterRegistry meterRegistry
  ) {
    var domainEventBus = new SpringDomainEventBus(publisher);
    var forwardOnlyDomainEventBus = new ForwardOnlyDomainEventBus(domainEventBus);
    return new MeteredDomainEventBus(forwardOnlyDomainEventBus, meterRegistry);
  }

}
