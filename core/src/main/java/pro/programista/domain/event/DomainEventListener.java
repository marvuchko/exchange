package pro.programista.domain.event;

public interface DomainEventListener<E extends DomainEvent<?>> {

  public void handleEvent(E event);

}
