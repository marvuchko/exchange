package pro.programista.infrastructure;

import java.io.Serializable;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheContext {

  public Cache<Serializable, Serializable> cache() {
    return new NoopCache();
  }

}
