package pro.programista.domain.event;

import java.util.List;
import pro.programista.domain.event.DomainEvent;

public interface EventStorage {

  void save(DomainEvent<?> event);

  List<DomainEvent<?>> toPublish();

  void published(List<DomainEvent<?>> events);

}
