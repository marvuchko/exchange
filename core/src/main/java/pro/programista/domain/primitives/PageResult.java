package pro.programista.domain.primitives;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResult<T> {

  List<T> content;
  long totalElements;
  long totalPages;
  long currentPage;

}
