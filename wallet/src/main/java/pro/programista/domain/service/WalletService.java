package pro.programista.domain.service;

import java.util.UUID;
import pro.programista.domain.entity.Wallet;
import pro.programista.domain.repository.WalletRepository;

public interface WalletService extends CrudService<UUID, Wallet, WalletRepository> {
}
