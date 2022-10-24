package pro.programista.infrastructure;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

public class NoopCache implements Cache<Serializable, Serializable> {
  @Override
  public Serializable get(Serializable id) {
    return null;
  }

  @Override
  public Set<Serializable> get(Set<Serializable> ids) {
    return Collections.emptySet();
  }

  @Override
  public void put(Serializable id, Serializable data) {

  }

  @Override
  public void put(Set<Serializable> ids, Set<Serializable> data) {

  }
}
