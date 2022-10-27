package pro.programista.infrastructure.context.event;

import io.micrometer.core.instrument.MeterRegistry;
import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import pro.programista.domain.event.DomainEventBus;
import pro.programista.domain.event.listener.UserEventHandlers;
import pro.programista.infrastructure.constants.UserBeanConstants;
import pro.programista.infrastructure.event.ForwardOnlyDomainEventBus;
import pro.programista.infrastructure.event.GenericDomainEventListener;
import pro.programista.infrastructure.event.MeteredDomainEventBus;
import pro.programista.infrastructure.event.SpringDomainEventBus;

@Configuration
public class UserEventContext {

  @Primary
  @Bean(UserBeanConstants.DOMAIN_EVENT_BUS)
  @ConditionalOnMissingBean(DomainEventBus.class)
  public DomainEventBus domainEventBus(
      ApplicationEventPublisher publisher,
      MeterRegistry meterRegistry
  ) {
    var domainEventBus = new SpringDomainEventBus(publisher);
    var forwardOnlyDomainEventBus = new ForwardOnlyDomainEventBus(domainEventBus);
    return new MeteredDomainEventBus(forwardOnlyDomainEventBus, meterRegistry);
  }

  @Bean(UserBeanConstants.USER_DOMAIN_EVENTS)
  public List<GenericDomainEventListener<?>> userDomainEventListeners() {
    return List.of(
        new UserEventHandlers.UserCreatedEventHandler(),
        new UserEventHandlers.UserUpdatedEventHandler(),
        new UserEventHandlers.UserDeletedEventHandler()
    );
  }

}
