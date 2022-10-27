package pro.programista.domain.service;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import pro.programista.domain.entity.DomainEntity;
import pro.programista.domain.event.DomainEventBus;
import pro.programista.domain.repository.DomainRepository;

@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public abstract class EventBusAwareCrudService<
    K extends Serializable,
    T extends DomainEntity<K>,
    R extends DomainRepository<K, T>> extends AbstractCrudService<K, T, R> {

  DomainEventBus eventBus;

  public EventBusAwareCrudService(R repository, DomainEventBus eventBus) {
    super(repository);
    this.eventBus = eventBus;
  }
}
