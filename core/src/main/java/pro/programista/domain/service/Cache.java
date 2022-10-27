package pro.programista.domain.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.util.ObjectUtils;

public interface Cache<K extends Serializable, T extends Serializable> {

  T get(K id);

  default List<T> get(List<K> ids) {
    return ids.stream().map(this::get).toList();
  }

  void put(K id, T data);

  default void put(List<K> ids, List<T> data) {
    if (ObjectUtils.isEmpty(ids) || ObjectUtils.isEmpty(data)) {
      return;
    }
    if (ids.size() != data.size()) {
      return;
    }
    for (int index = 0; index < ids.size(); ++index) {
      put(ids.get(index), data.get(index));
    }
  }

  void remove(K id);

  default void remove(List<K> ids) {
    ids.forEach(this::remove);
  }

}
