package pro.programista.infrastructure.event;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import pro.programista.domain.event.DomainEvent;
import pro.programista.domain.event.DomainEventBus;
import pro.programista.domain.event.EventConstants;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MeteredDomainEventBus implements DomainEventBus {

  DomainEventBus delegate;
  MeterRegistry metricsRegistry;

  @Override
  public void publish(DomainEvent<?> event) {
    delegate.publish(event);

    log.info("Event {} with eventId: {} has been published!",
        event.getClass().getSimpleName(),
        event.aggregateId());

    metricsRegistry.counter(
        EventConstants.DOMAIN_EVENTS,
        EventConstants.NAME_TAG,
        event.getClass().getSimpleName()).increment();
  }
}
