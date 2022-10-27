package pro.programista.domain.utility;

import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Ensure {

  public static void notEmpty(Object value, String message) {
    if (ObjectUtils.isEmpty(value)) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void notNull(Object value, String message) {
    if (Objects.isNull(value)) {
      throw new IllegalArgumentException(message);
    }
  }

}
