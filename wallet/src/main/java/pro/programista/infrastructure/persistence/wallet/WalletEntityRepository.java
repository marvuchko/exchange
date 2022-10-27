package pro.programista.infrastructure.persistence.wallet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import pro.programista.domain.entity.Wallet;
import pro.programista.domain.primitives.PageResult;
import pro.programista.domain.repository.WalletRepository;
import pro.programista.domain.value.Balance;
import pro.programista.domain.value.Currency;
import pro.programista.domain.value.CurrencyPair;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WalletEntityRepository implements WalletRepository {

  WalletJpaRepository walletJpaRepository;

  @Override
  public Optional<Wallet> findById(UUID id) {
    return walletJpaRepository.findById(id)
        .map(this::toDomain);
  }

  @Override
  public List<Wallet> findByIds(List<UUID> ids) {
    return walletJpaRepository.findAllById(ids)
        .stream()
        .map(this::toDomain)
        .toList();
  }

  @Override
  public PageResult<Wallet> findPage(long page, long size) {
    var pageData = walletJpaRepository.findAll(PageRequest.of((int) page, (int) size))
        .map(this::toDomain);

    return new PageResult<>(
        pageData.getContent(),
        pageData.getTotalElements(),
        pageData.getTotalPages(),
        pageData.getNumber()
    );
  }

  @Override
  public Wallet createOrUpdate(Wallet data) {
    var entity = walletJpaRepository.save(toJpaEntity(data));
    return toDomain(entity);
  }

  @Override
  public void delete(UUID id) {
    walletJpaRepository.deleteById(id);
  }

  private Wallet toDomain(WalletJpaEntity entity) {
    var wallet = new Wallet();
    wallet.setId(entity.getId());

    var balance = new Balance(entity.getBalance());
    var currency = new Currency(entity.getCurrency());
    var currencyPair = new CurrencyPair(currency, balance);

    wallet.setCurrencyPair(currencyPair);
    return wallet;
  }

  private WalletJpaEntity toJpaEntity(Wallet wallet) {
    var entity = new WalletJpaEntity();

    entity.setId(wallet.getId());
    entity.setBalance(wallet.getCurrencyPair().balance().value());
    entity.setCurrency(wallet.getCurrencyPair().currency().currency());

    return entity;
  }

}
