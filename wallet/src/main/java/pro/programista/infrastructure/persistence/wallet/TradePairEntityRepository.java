package pro.programista.infrastructure.persistence.wallet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import pro.programista.domain.entity.TradePair;
import pro.programista.domain.primitives.PageResult;
import pro.programista.domain.repository.TradePairRepository;
import pro.programista.domain.value.TradeCurrency;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TradePairEntityRepository implements TradePairRepository {

  TradePairJpaRepository tradePairJpaRepository;

  @Override
  public Optional<TradePair> findById(UUID id) {
    return tradePairJpaRepository.findById(id).map(this::toDomain);
  }

  @Override
  public List<TradePair> findByIds(List<UUID> ids) {
    return tradePairJpaRepository.findAllById(ids).stream()
        .map(this::toDomain)
        .toList();
  }

  @Override
  public PageResult<TradePair> findPage(long page, long size) {
    var pageData = tradePairJpaRepository.findAll(PageRequest.of((int) page, (int) size))
        .map(this::toDomain);

    return new PageResult<>(
        pageData.getContent(),
        pageData.getTotalElements(),
        pageData.getTotalPages(),
        pageData.getNumber()
    );
  }

  @Override
  public TradePair createOrUpdate(TradePair data) {
    var entity = toEntity(data);
    return toDomain(tradePairJpaRepository.save(entity));
  }

  @Override
  public void delete(UUID id) {
    tradePairJpaRepository.deleteById(id);
  }

  TradePair toDomain(TradePairJpaEntity entity) {
    var tradePair = new TradePair();
    tradePair.setId(entity.getId());

    var firstTradeCurrency = entity.getFirstTradeCurrency();
    var secondTradeCurrency = entity.getSecondTradeCurrency();

    tradePair.setFirstCurrency(new TradeCurrency(firstTradeCurrency.getId(), firstTradeCurrency.getName()));
    tradePair.setSecondCurrency(new TradeCurrency(secondTradeCurrency.getId(), secondTradeCurrency.getName()));

    return tradePair;
  }

  TradePairJpaEntity toEntity(TradePair tradePair) {
    var entity = new TradePairJpaEntity();

    var firstCurrency = new TradeCurrencyJpaEntity();
    firstCurrency.setId(tradePair.getFirstCurrency().id());
    firstCurrency.setName(tradePair.getFirstCurrency().name());

    var secondCurrency = new TradeCurrencyJpaEntity();
    secondCurrency.setId(tradePair.getSecondCurrency().id());
    secondCurrency.setName(tradePair.getSecondCurrency().name());

    entity.setId(tradePair.getId());
    entity.setFirstTradeCurrency(firstCurrency);
    entity.setSecondTradeCurrency(secondCurrency);

    return entity;
  }

}
