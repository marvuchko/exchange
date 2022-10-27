package pro.programista.domain.command;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import pro.programista.domain.event.DomainEventBus;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class CommandHandler {

  DomainEventBus eventBus;

}
