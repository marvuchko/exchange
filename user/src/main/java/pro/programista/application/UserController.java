package pro.programista.application;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.programista.application.controller.RestMvcController;
import pro.programista.domain.entity.User;
import pro.programista.domain.primitives.PageResult;
import pro.programista.domain.primitives.Result;
import pro.programista.domain.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping(UserController.BASE_URL)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController extends RestMvcController {

  public static final String BASE_URL = "/users";

  UserService userService;

  @GetMapping
  public ResponseEntity<Result<PageResult<User>>> getPage(@RequestParam long page, @RequestParam long size) {
    return resolve(userService.findPage(page, size));
  }

  @GetMapping(ID_PATH)
  public ResponseEntity<Result<User>> getById(@PathVariable UUID id) {
    return resolve(userService.findById(id));
  }

  @DeleteMapping(ID_PATH)
  public ResponseEntity<Result<User>> deleteById(@PathVariable UUID id) {
    return resolve(userService.delete(id));
  }

}
