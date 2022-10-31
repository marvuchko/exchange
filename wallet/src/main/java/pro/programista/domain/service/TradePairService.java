package pro.programista.domain.service;

import java.util.UUID;
import pro.programista.domain.entity.TradePair;
import pro.programista.domain.repository.TradePairRepository;

public interface TradePairService extends CrudService <UUID, TradePair, TradePairRepository> {
}
