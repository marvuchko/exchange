package pro.programista.infrastructure.event;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEventPublisher;
import pro.programista.domain.event.DomainEvent;
import pro.programista.domain.event.DomainEventBus;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SpringDomainEventBus implements DomainEventBus {

  ApplicationEventPublisher publisher;

  @Override
  public void publish(DomainEvent<?> domainEvent) {
    publisher.publishEvent(domainEvent);
  }

}
