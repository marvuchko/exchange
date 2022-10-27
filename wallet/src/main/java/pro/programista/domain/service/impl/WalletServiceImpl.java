package pro.programista.domain.service.impl;

import java.util.UUID;
import pro.programista.domain.entity.Wallet;
import pro.programista.domain.event.DomainEventBus;
import pro.programista.domain.repository.WalletRepository;
import pro.programista.domain.service.EventBusAwareCrudService;
import pro.programista.domain.service.WalletService;

public class WalletServiceImpl
    extends EventBusAwareCrudService<UUID, Wallet, WalletRepository>
    implements WalletService {

  public WalletServiceImpl(WalletRepository repository, DomainEventBus eventBus) {
    super(repository, eventBus);
  }

}
