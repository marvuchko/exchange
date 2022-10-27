package pro.programista.domain.event;

import java.util.List;

public interface DomainEventBus {

  void publish(DomainEvent<?> domainEvent);

  default void publish(List<DomainEvent<?>> domainEvents) {
    domainEvents.forEach(this::publish);
  }

}
