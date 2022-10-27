package pro.programista.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pro.programista.domain.event.DomainEvent;

public class AggregateRoot<K extends Serializable> extends DomainEntity<K> {

  private final List<DomainEvent<?>> domainEvents = new ArrayList<>();

  public List<DomainEvent<?>> getDomainEvents() {
    return Collections.unmodifiableList(domainEvents);
  }

  public void addDomainEvent(DomainEvent<?> event) {
    domainEvents.add(event);
  }

  public void clearDomainEvents() {
    domainEvents.clear();
  }

}
