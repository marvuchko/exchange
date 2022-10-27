package pro.programista.infrastructure.event;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import pro.programista.domain.event.DomainEvent;
import pro.programista.domain.event.EventStorage;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InMemoryEventStorage implements EventStorage {

  List<DomainEvent<?>> eventList = Collections.synchronizedList(new ArrayList<>());

  @Override
  public synchronized void save(DomainEvent<?> event) {
    eventList.add(event);
  }

  @Override
  public synchronized List<DomainEvent<?>> toPublish() {
    return eventList.stream()
        .filter(event -> Instant.now().isAfter(event.publishTimestamp()))
        .toList();
  }

  @Override
  public synchronized void published(List<DomainEvent<?>> events) {
    eventList.removeAll(events);
  }

}
