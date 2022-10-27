package pro.programista.infrastructure.cache;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import pro.programista.domain.service.Cache;

public class NoopCache<K extends Serializable, T extends Serializable> implements Cache<K, T> {

  @Override
  public T get(K id) {
    return null;
  }

  @Override
  public List<T> get(List<K> ids) {
    return Collections.emptyList();
  }

  @Override
  public void put(K id, T data) {
    // Do nothing
  }

  @Override
  public void remove(K id) {
    // Do nothing
  }

}
