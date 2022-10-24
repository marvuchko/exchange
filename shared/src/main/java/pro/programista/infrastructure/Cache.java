package pro.programista.infrastructure;

import java.io.Serializable;
import java.util.Set;

public interface Cache<K extends Serializable, T extends Serializable> {

  T get(K id);

  Set<T> get(Set<K> ids);

  void put(K id, T data);

  void put(Set<K> ids, Set<T> data);

}
