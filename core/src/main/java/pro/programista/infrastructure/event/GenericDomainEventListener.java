package pro.programista.infrastructure.event;

import org.springframework.context.event.EventListener;
import pro.programista.domain.event.DomainEvent;
import pro.programista.domain.event.DomainEventListener;

public abstract class GenericDomainEventListener<E extends DomainEvent<?>>
    implements DomainEventListener<E> {

  @EventListener
  private void onDomainEvent(E event) {
    handleEvent(event);
  }

}
