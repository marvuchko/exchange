package pro.programista.application;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pro.programista.domain.entity.User;
import pro.programista.domain.service.UserService;

@RestController
public class UserController extends AbstractMvcController<UserService> {

  public UserController(UserService service) {
    super(service);
  }

  @GetMapping
  public ResponseEntity<Page<User>> getPage(Pageable pageable) {
    return ResponseEntity.ok()
        .body(service.findPage(pageable));
  }

  @GetMapping(ID_PATH)
  public ResponseEntity<User> getById(@PathVariable UUID id) {
    return ResponseEntity.ok()
        .body(service.findById(id).get());
  }

}
