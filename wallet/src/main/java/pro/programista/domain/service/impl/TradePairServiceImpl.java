package pro.programista.domain.service.impl;

import java.util.UUID;
import pro.programista.domain.entity.TradePair;
import pro.programista.domain.repository.TradePairRepository;
import pro.programista.domain.service.AbstractCrudService;
import pro.programista.domain.service.TradePairService;

public class TradePairServiceImpl
    extends AbstractCrudService<UUID, TradePair, TradePairRepository>
    implements TradePairService {

  public TradePairServiceImpl(TradePairRepository repository) {
    super(repository);
  }

}
