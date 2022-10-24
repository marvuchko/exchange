package pro.programista.application;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import pro.programista.domain.service.CrudService;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public class AbstractMvcController<S extends CrudService<?, ?, ?>> {

  protected static final String ID_PATH = "/{id}";
  protected static final String ID_PARAM = "id";

  S service;

}
