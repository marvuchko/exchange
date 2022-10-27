package pro.programista.infrastructure.event;

import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Scheduled;
import pro.programista.domain.event.DomainEvent;
import pro.programista.domain.event.DomainEventBus;
import pro.programista.domain.event.EventConstants;
import pro.programista.domain.event.EventStorage;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StoreAndForwardDomainEventBus implements DomainEventBus {

  EventStorage eventStorage;
  DomainEventBus delegate;

  @Override
  public void publish(DomainEvent<?> domainEvent) {
    eventStorage.save(domainEvent);
  }

  @Scheduled(cron = EventConstants.EVENT_PUBLISHING_CRON)
  public void publishAllPeriodically() {
    List<DomainEvent<?>> domainEvents = eventStorage.toPublish();
    domainEvents.forEach(delegate::publish);
    eventStorage.published(domainEvents);
  }

}
